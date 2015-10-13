package zcu.jaranalyzer;

import cz.zcu.kiv.jacc.javatypes.JClass;
import cz.zcu.kiv.jacc.javatypes.JField;
import cz.zcu.kiv.jacc.javatypes.JMethod;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wikif on 23.7.15.
 */
@XmlRootElement(name = "class")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnalyzerClass {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "super")
    private String superName;
    @XmlElementWrapper(name = "fields")
    @XmlElement(name = "field")
    private Set<AnalyzerField> fields = new HashSet<AnalyzerField>();
    @XmlElementWrapper(name = "methods")
    @XmlElement(name = "method")
    private Set<AnalyzerMethod> methods = new HashSet<AnalyzerMethod>();
    @XmlElementWrapper(name = "interfaces")
    @XmlElement(name = "interface")
    private Set<String> interfaces = new HashSet<String>();
    @XmlElementWrapper(name = "modifiers")
    @XmlElement(name = "modifier")
    private Set<String> modifiers = new HashSet<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public Set<AnalyzerField> getFields() {
        return fields;
    }

    public void setFields(Set<AnalyzerField> fields) {
        this.fields = fields;
    }

    public Set<AnalyzerMethod> getMethods() {
        return methods;
    }

    public void setMethods(Set<AnalyzerMethod> methods) {
        this.methods = methods;
    }

    public Set<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Set<String> interfaces) {
        this.interfaces = interfaces;
    }

    public Set<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(Set<String> modifiers) {
        this.modifiers = modifiers;
    }

    public static AnalyzerClass ConvertFromJClass(JClass jc){
        AnalyzerClass ac = new AnalyzerClass();
        ac.setName(jc.getName());
        ac.setSuperName(jc.getSuperclass() != null ? jc.getSuperclass().getName() : "");
        for(JClass i : jc.getInterfaces()){
            ac.getInterfaces().add(i.getName());
        }
        for(JField f : jc.getDeclaredFields()){
            ac.getFields().add(AnalyzerField.ConvertFromJField(f));
        }
        for(JMethod m : jc.getDeclaredMethods()){
            ac.getMethods().add(AnalyzerMethod.ConvertsFromJMethod(m));
        }
        ac.getModifiers().addAll(Arrays.asList(jc.getModifiers().toString().split(" ")));
        return ac;
    }
}
