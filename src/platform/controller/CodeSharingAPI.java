package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.business.CodeSharingService;
import platform.business.CodeSnippet;
import platform.persistence.dto.CodeSnippetDto;
import platform.util.Dates;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("api")
public class CodeSharingAPI {

    public static final String RESTRICTED_CODE = "Restricted Code";
    private final CodeSharingService codeSharingService;

    @Autowired
    public CodeSharingAPI(CodeSharingService codeSharingService) {
        this.codeSharingService = codeSharingService;
    }

    @PostMapping(path = "/code/new")
    public ResponseEntity<String> createCodeSnippet(@RequestBody CodeSnippet codeSnippet) {
        String id = UUID.randomUUID().toString();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeLimit = now.plusSeconds(codeSnippet.getTime());
        int remainingTimeInSeconds = (int) ChronoUnit.SECONDS.between(now, timeLimit);

        codeSnippet.setId(id);
        codeSnippet.setDate(now);

        boolean restrictedByTime = remainingTimeInSeconds > 0;
        boolean restrictedByViews = codeSnippet.getViews() > 0;

        if (restrictedByTime) {
            codeSnippet.setRestricted(true);
            codeSnippet.setRestrictedByTime(true);
        }

        if (restrictedByViews) {
            codeSnippet.setRestricted(true);
            codeSnippet.setRestrictedByViews(true);
        }

        System.out.println("API:createCodeSnippet = " + codeSnippet);
        return ResponseEntity.ok(String.format("{\"id\" : \"%s\"}", codeSharingService.saveCodeSnippet(codeSnippet)));
    }

    @GetMapping(path = "/code/{uuid}")
    public ResponseEntity<CodeSnippetDto> findCodeSnippetById(@PathVariable String uuid) {
        CodeSnippet codeSnippet = codeSharingService.findCodeSnippetById(uuid);
        System.out.println("API:findCodeSnippetById = " + codeSnippet);

        int remainingTimeInSeconds = 0;
        int views = 0;

        if (codeSnippet.isRestricted()) {

            System.out.println("remainingTimeInSeconds = " + remainingTimeInSeconds);

            if (codeSnippet.isRestrictedByViews()) {
                if (codeSnippet.getViews() > 0) {
                    views = codeSnippet.getViews() - 1;
                    codeSnippet.setViews(views);
                    codeSharingService.saveCodeSnippet(codeSnippet);
                } else {
                    return404();
                }
            }

            if (codeSnippet.isRestrictedByTime()) {
                LocalDateTime timeLimit = codeSnippet.getDate().plusSeconds(codeSnippet.getTime());
                remainingTimeInSeconds = (int) ChronoUnit.SECONDS.between(LocalDateTime.now(), timeLimit);
                if (remainingTimeInSeconds <= 0) {
                    return404();
                }
            }
        }

        return ResponseEntity.ok(new CodeSnippetDto(codeSnippet.getCode(),
                Dates.toString(codeSnippet.getDate()),
                remainingTimeInSeconds,
                views,
                codeSnippet.isRestricted(),
                codeSnippet.isRestrictedByTime(),
                codeSnippet.isRestrictedByViews()));
    }

    private void return404() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, RESTRICTED_CODE);
    }

    @GetMapping(path = "/code/latest")
    public ResponseEntity<List<CodeSnippetDto>> getLatestPublicCodeSnippets() {
        List<CodeSnippetDto> codeSnippets = new ArrayList<>();
        for (CodeSnippet codeSnippet : codeSharingService.getLatestPublicCodeSnippetsOrderByDate()) {
            CodeSnippetDto codeSnippetDto = new CodeSnippetDto(
                    codeSnippet.getCode(),
                    Dates.toString(codeSnippet.getDate()),
                    codeSnippet.getTime(),
                    codeSnippet.getViews(),
                    codeSnippet.isRestricted(),
                    codeSnippet.isRestrictedByTime(),
                    codeSnippet.isRestrictedByViews());
            codeSnippets.add(codeSnippetDto);
        }
        return ResponseEntity.ok(codeSnippets);
    }
}
