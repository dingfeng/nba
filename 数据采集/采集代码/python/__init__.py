#coding=utf-8
# -*- coding: UTF-8 -*- 
 #@author dingfeng 
 #@date 2015/5/2
import urllib2
import sys
import chardet
import urllib
import json
import MyFuction
def scrap():
    filepath1 = 'I:/mydata/pregame14.txt'
    filepath2 = 'I:/mydata/regulargame14.txt'
    filepath4 = 'I:/mydata/postgame14.txt'
    type1 = '00114' #pregame
    type2 = '00214'  #regular
    type4 = '00414'  #postgame
    try:
     MyFuction.scrap14Games(filepath1,type1)
    except Exception,e:
        print e
    try:
        MyFuction.scrap14Games(filepath2, type2)
    except Exception,e:
        print e
    try:
        MyFuction.scrap14Games(filepath4,type4)
    except Exception,e:
        print e
    return

