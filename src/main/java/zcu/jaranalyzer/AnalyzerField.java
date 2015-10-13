package zcu.jaranalyzer;

import cz.zcu.kiv.jacc.javatypes.JField;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wikif on 23.7.15.
 */
@XmlRootElement(name = "field")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnalyzerField {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "type")
    private String type;
    @XmlElementWrapper(name = "modifiers")
    @XmlElement(name = "modifier")
    private Set<String> modifiers = new HashSet<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(Set<String> modifiers) {
        this.modifiers = modifiers;
    }

    public static AnalyzerField ConvertFromJField(JField jf){
        AnalyzerField af = new AnalyzerField();
        af.setName(jf.getName());
        af.setType(jf.getType().getName());
        af.getModifiers().addAll(Arrays.asList(jf.getModifiers().toString().split(" ")));
        return af;
    }
}
