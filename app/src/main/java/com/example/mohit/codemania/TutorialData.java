package com.example.mohit.codemania;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class TutorialData {

    ArrayList<Tutorial> tutorialdata;
    ArrayList<Tutorial> tutorialdataHE;
    String[] head, desc, ur, site;
    private final int MAX_TUT = 1000;
    private int t, u, n;
    String constUrlTC, constUrlHE;

    TutorialData()
    {
        tutorialdata = new ArrayList<>();
        tutorialdataHE = new ArrayList<>();
        head = new String[MAX_TUT];
        desc = new String[MAX_TUT];
        ur = new String[MAX_TUT];
        site = new String[MAX_TUT];
        t = 20; u = 10;
        constUrlTC = "https://www.topcoder.com/community/data-science/data-science-tutorials/";
        constUrlHE = "https://www.hackerearth.com/practice/notes/";
    }

    public void initializeStringTC()
    {
        head[0] = "The Importance of Algorithms";
        head[1] = "How To Dissect a Topcoder Problem Statement";
        head[2] = "How to Find a Solution";
        head[3] = "Planning an Approach to a Topcoder Problem Part 1";
        head[4] = "Planning an Approach to a Topcoder Problem Part 2";
        head[5] = "Mathematics for Topcoders";
        head[6] = "Geometry Concepts Basic Concepts";
        head[7] = "Geometry Concepts Line Intersection and its Applications";
        head[8] = "Geometry Concepts Using Geometry in Topcoder Problems";
        head[9] = "Introduction to Graphs and Their Data Structures Section 1";
        head[10] = "Introduction to Graphs and Their Data Structures Section 2";
        head[11] = "Introduction to Graphs and Their Data Structures Section 3";
        head[12] = "Greedy is Good";
        head[13] = "Dynamic Programming: From Novice to Advanced";
        head[14] = "Computational Complexity Section 1";
        head[15] = "Computational Complexity Section 2";
        head[16] = "Understanding Probabilities";
        head[17] = "Data Structures";
        head[18] = "Sorting";
        head[19] = "Binary Search";

        int i;
        String temp[];
        for(i=0;i<t;i++)
        {
            temp = head[i].toLowerCase().split(" ");
            int l = temp.length;
            StringBuilder sb = new StringBuilder();
            for(int j = 0;j<l;j++) sb.append(temp[j]).append("-");
            String sbs = sb.toString();
            ur[i] = constUrlTC+sbs.substring(0, sbs.length()-1)+"/";
            site[i] = "Topcoder";



        }

        desc[0] = "The first step towards an understanding of why the " +
                "study and knowledge of algorithms are so important is to define " +
                "exactly what we mean by an algorithm.";
        desc[1] = "How many times has this happened to you: " +
                "you register for the SRM, go into your assigned room " +
                "when the system tells you to, and when the match starts, you open the 250… and find it";
        desc[2] = "With many topcoder problems, the solutions may be found " +
                "instantly just by reading their descriptions. This is possible " +
                "thanks to a collection of common traits that problems with similar solutions often have.";
        desc[3] = "Planning an approach is a finicky art; it can stump the most seasoned coders as much as it stumps the newer ones, and it can be extremely hard to put into words. It can involve many calculations and backtracks, as well as foresight, intuition, creativity, and even dum";
        desc[4] = "This technique is the antithesis to breaking down a program, and should be the first thing you start doing when you get stuck. Bottom-up programming is the process of building up primitive functions into more functional code until the solution becomes as trivial as one of the primitives. ";
        desc[5] = "I have seen a number of competitors complain that they are unfairly disadvantaged because many topcoder problems are too mathematical. Personally, I love mathematics and thus I am biased in this issue. Nevertheless, I strongly believe that problems should co";
        desc[6] = "Many topcoders seem to be mortally afraid of geometry problems. I think it’s safe to say that the majority of them would be in favor of a ban on topcoder geometry problems.";
        desc[7] = "In the previous section we saw how to use vectors to solve geometry problems. Now we are going to learn how to use some basic linear algebra to do line intersection, and then apply line intersection to a couple of other problems. ";
        desc[8] = "First off, we can use our Line-Point Distance code to test for the \"BOUNDARY\" case. If the distance from any segment to the test point is 0, then return \"BOUNDARY\".";
        desc[9] = "Graphs are a fundamental data structure in the world of programming, and this is no less so on topcoder. Usually appearing as the hard problem in Division 2, or the medium or hard problem in Division 1, there are many different forms solving a graph problem can take";
        desc[10] = "So far we have learned how to represent our graph in memory, but now we need to start doing something with this information. There are two methods for searching graphs that are extremely prevalent, and will form the foundations for more advanced algorithms later on.";
        desc[11] = "An extremely common problem on topcoder is to find the shortest path from one position to another. There are a few different ways for going about this, each of which has different uses. We will be discussing two different methods, Dijkstra using a Heap and the Floyd Warshall method. ";
        desc[12] = "John Smith is in trouble! He is a topcoder member and once he learned to master the \"Force\" of dynamic programming, he began solving problem after problem. But his once obedient computer acts quite unfriendly today. Following his usual morning ritual, John woke up at 10 AM, ";
        desc[13] = "An important part of given problems can be solved with the help of dynamic programming (DP for short). Being able to tackle problems of this type would greatly increase your skill. I will try to help you in understanding how to solve problems using DP. ";
        desc[14] = "In this article I’ll try to introduce you to the area of computation complexity. The article will be a bit long before we get to the actual formal definitions because I feel that the rationale behind these definitions needs to be explained as well – and that understanding the rationa";
        desc[15] = "In this part of the article we will focus on estimating the time complexity for recursive programs. In essence, this will lead to finding the order of growth for solutions of recurrence equations.";
        desc[16] = "It has been said that life is a school of probability. A major effect of probability theory on everyday life is in risk assessment.";
        desc[17] = "Even though computers can perform literally millions of mathematical computations per second, when a problem gets large and complicated, ";
        desc[18] = "Any number of practical applications in computing require things to be in order. Even before we start computing, the importance of sorting is drilled into us.";
        desc[19] = "Binary search is one of the fundamental algorithms in computer science. In order to explore it, we’ll first build up a theoretical backbone, then use that";
    }

    public void initializeStringHE()
    {
        head[0] = "Array And Strings Code Monk";
        head[1] = "Sorting Code Monk";
        head[2] = "Searching Code Monk";
        head[3] = "Stacks and Queues";
        head[4] = "Number Theory 1";
        head[5] = "Number Theory II";
        head[6] = "Graph Theory Part I";
        head[7] = "Graph Theory Part II";
        head[8] = "Dynamic Programming I";
        head[9] = "Bit Manipulation";

        for(int i = 0; i<u; i++)
        {
            String temp[] = head[i].toLowerCase().split(" ");
            int l = temp.length;
            StringBuilder sb = new StringBuilder();
            for(int j = 0;j<l;j++) sb.append(temp[j]).append("-");
            String s = sb.toString();
            ur[i] = constUrlHE + s.substring(0, s.length()-1) + "/";
            site[i] = "Hackerearth";
        }

        desc[0] = "An array is a sequential collection of " +
                "variables of same data type which can be " +
                "accessed using an integer as index, that generally starts from 0.";
        desc[1] = "Sorting is a process of arranging items in ascending or descending order." +
                " This process can be implemented via many different algorithms." +
                "Following is the list of sorting algorithms which";
        desc[2] = "Searching is one of the most fundamental problems " +
                "in Computer Science. It is the " +
                "process of finding a particular item in a collection of items.";
        desc[3] = "We deal with data all the time, so how we store, organise or group our data, matters."+
                "Data Structures are tools which are used to store data " +
                "in a structured way in computer to use it efficiently. ";
        desc[4] = "Problems in competitive programming which involve " +
                "Mathematics are are usually about number theory, or geometry. ";
        desc[5] = "Problems in competitive programming which " +
                "involve Mathematics are are usually about number theory, or geometry.";
        desc[6] = "What is a graph? Do we use it a lot of times? Let’s think of an example: " +
                "Facebook. The humongous network of you, your friends, " +
                "family, their friends and their friends e";
        desc[7] = "We will start with one of the most studied and very interesting " +
                "problem in graph theory - finding shortest paths between vertices.";
        desc[8] = "The image above says a lot about Dynamic Programming. So, is repeating " +
                "the things for which you already have the answer, a good thing ? ";
        desc[9] = "Working on bytes, or data types comprising of bytes like ints, floats, " +
                "doubles or even data structures which stores large " +
                "amount of bytes is normal for a programmer.";
    }


    public ArrayList<Tutorial> getTutorialData()
    {
        initializeStringTC();
        int i;
        for(i=0;i<t;i++)
        {
            tutorialdata.add(new Tutorial(head[i], desc[i], ur[i], site[i]));
        }
        return tutorialdata;
    }

    public ArrayList<Tutorial> getTutorialdataHE()
    {
        initializeStringHE();
        int i;
        for(i=0;i<u;i++)
        {
            tutorialdataHE.add(new Tutorial(head[i], desc[i], ur[i], site[i]));
        }
        return tutorialdataHE;
    }


}
