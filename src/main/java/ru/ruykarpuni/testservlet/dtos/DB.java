package ru.ruykarpuni.testservlet.dtos;


import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DB {
    ServletContext mContext;
    public void readdata(ServletContext context){
mContext = context;
       mContext.log("test");
    }
}
