package platform.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.business.CodeSnippet;

import java.util.List;

@Repository
public interface CodeRepository extends CrudRepository<CodeSnippet, String> {

    List<CodeSnippet> findTop10ByRestrictedFalseOrderByDateDesc();
}
