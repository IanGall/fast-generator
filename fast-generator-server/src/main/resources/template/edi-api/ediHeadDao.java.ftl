package ${package}<#if moduleName??>.${moduleName}</#if><#if subModuleName??>.${subModuleName}</#if>;

import ${entityPackage}.${ClassName};
import com.dcjet.edi.core.base.dao.BasicDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ${ClassName}Dao extends BasicDao<${ClassName}> {
	
}