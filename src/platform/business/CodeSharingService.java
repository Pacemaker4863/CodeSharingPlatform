package platform.business;

import org.springframework.stereotype.Service;
import platform.persistence.CodeRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CodeSharingService {

    private final CodeRepository codeRepository;

    public CodeSharingService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public String saveCodeSnippet(CodeSnippet codeSnippet) {
        return codeRepository.save(codeSnippet).getId();
    }

    public CodeSnippet findCodeSnippetById(String id) {
        return codeRepository.findById(id).orElse(null);
    }

    public List<CodeSnippet> getLatestPublicCodeSnippetsOrderByDate() {
        return codeRepository.findTop10ByRestrictedFalseOrderByDateDesc();
    }
}
