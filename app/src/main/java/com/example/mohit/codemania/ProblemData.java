package com.example.mohit.codemania;

/**
 * Created by shubham on 21/3/17.
 */

//problem data class
public class ProblemData {
    private int id;
    private String index;
    private String name;
    private long solvedCount;
    private boolean solvedornot;
    ProblemData(int pid,String idx,String pname,long solvedcnt){
        id = pid;
        index = idx;
        name = pname;
        solvedCount =solvedcnt;
        solvedornot =false;
    }
    public int getid(){
        return id;
    }
    public  String getIndex(){
        return index;
    }
    public String getName(){
        return name;
    }
    public long getSolvedCount(){
        return solvedCount;
    }
    public boolean isSolved(){return solvedornot;}
}
