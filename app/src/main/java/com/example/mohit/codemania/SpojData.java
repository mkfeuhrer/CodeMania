package com.example.mohit.codemania;

/**
 * Created by prasoon on 24/3/17.
 */

public class SpojData {
    private String name, handle, place, joined, rank, institution,
        problemSolved, solutionSubmitted, points;

    public SpojData(String n, String h, String p, String j, String r, String i, String pr, String s, String poi)
    {
        name = n;
        handle = h;
        place = p;
        joined = j;
        rank = r;
        institution = i;
        problemSolved = pr;
        solutionSubmitted = s;
        points = poi;
    }

    public SpojData()
    {

    }

    public void setPoints(String points)
    {
        this.points = points;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void setHandle(String handle)
    {
        this.handle = handle;
    }
    public void setPlace(String place)
    {
        this.place = place;
    }
    public void setJoined(String joined)
    {
        this.joined = joined;
    }
    public void setRank(String rank)
    {
        this.rank = rank;
    }
    public void setInstitution(String institution)
    {
        this.institution = institution;
    }
    public void setProblemSolved(String problemSolved)
    {
        this.problemSolved = problemSolved;
    }
    public void setSolutionSubmitted(String solutionSubmitted)
    {
        this.solutionSubmitted = solutionSubmitted;
    }

    public String getName()
    {
        return name;
    }
    public String getHandle()
    {
        return handle;
    }
    public String getPlace()
    {
        return place;
    }
    public String getJoined()
    {
        return joined;
    }
    public String getRank()
    {
        return rank;
    }
    public String getInstitution()
    {
        return institution;
    }
    public String getProblemSolved()
    {
        return problemSolved;
    }
    public String getSolutionSubmitted()
    {
        return solutionSubmitted;
    }

    public String getPoints()
    {
        return points;
    }
}
