package zcu.jaranalyzer;

import cz.zcu.kiv.jacc.javatypes.JMethod;
import cz.zcu.kiv.jacc.javatypes.JType;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wikif on 23.7.15.
 */
@XmlRootElement(name = "method")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnalyzerMethod {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "return")
    private String returnType;
    @XmlElementWrapper(name = "modifiers")
    @XmlElement(name = "modifier")
    private Set<String> modifiers = new HashSet<String>();
    @XmlElementWrapper(name = "parameters")
    @XmlElement(name = "parameter")
    private Set<String> paramTypes = new HashSet<String>();
    @XmlElementWrapper(name = "exceptions")
    @XmlElement(name = "exception")
    private Set<String> exceptions = new HashSet<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public Set<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(Set<String> modifiers) {
        this.modifiers = modifiers;
    }

    public Set<String> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Set<String> paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Set<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(Set<String> exceptions) {
        this.exceptions = exceptions;
    }

    public static AnalyzerMethod ConvertsFromJMethod(JMethod jm){
        AnalyzerMethod am = new AnalyzerMethod();
        am.setName(jm.getName());
        am.setReturnType(jm.getReturnType().getName());
        for(JType ex: jm.getExceptionTypes()){
            am.getExceptions().add(ex.getName());
        }
        for(JType param: jm.getParameterTypes()){
            am.getParamTypes().add(param.getName());
        }
        am.getModifiers().addAll(Arrays.asList(jm.getModifiers().toString().split(" ")));
        return am;
    }
}
