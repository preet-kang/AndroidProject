package kang.amy.c0695706_exam2;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by amykang on 2017-08-10.
 */

public class Employe extends RealmObject
{
    @Required
    private String employeId;
    @Required
    @PrimaryKey
    private String employeName;
    @Required
    private String employeDob;
    @Required
    private String employeSalary;

    public void setEmployeId(String employeId)
    {
        this.employeId = employeId;
    }

    public void setEmployeName(String employeName)
    {
        this.employeName = employeName;
    }

    public void setEmployeDob(String employeDob)
    {
        this.employeDob = employeDob;
    }

    public void setEmployeSalary(String employeSalary)
    {
        this.employeSalary = employeSalary;
    }



    public String getEmployeId()
    {
        return this.employeId;
    }

    public String getEmployeName()
    {
        return this.employeName;
    }

    public String getEmployeDob()
    {
        return this.employeDob;

    }

    public String getEmployeSalary()
    {
        return this.employeSalary;
    }

}
