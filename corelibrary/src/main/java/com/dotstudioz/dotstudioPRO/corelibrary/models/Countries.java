package com.dotstudioz.dotstudioPRO.corelibrary.models;

public class Countries
{
    private String id;

    private String name;

    private String sortname;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getSortname ()
    {
        return sortname;
    }

    public void setSortname (String sortname)
    {
        this.sortname = sortname;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", name = "+name+", sortname = "+sortname+"]";
    }
}