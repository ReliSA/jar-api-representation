package zcu.jaranalyzer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wikif on 23.7.15.
 */
@XmlRootElement(name = "jar")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"jarName", "classes"})
public class AnalyzerSummary {
    @XmlElement(name = "class", type = AnalyzerClass.class)
    private Set<AnalyzerClass> classes = new HashSet<AnalyzerClass>();
    @XmlElement(name = "jarName")
    private String jarName; 
    
    public String getJarName() {
		return jarName;
	}

	public void setJarName(String jarName) {
		this.jarName = jarName;
	}

	public Set<AnalyzerClass> getClasses() {
        return classes;
    }

    public void setClasses(Set<AnalyzerClass> classes) {
        this.classes = classes;
    }
}
