#coding=utf-8
# -*- coding: UTF-8 -*- 
import MyFuction 
import json
matches = open('K:/mydata/14-15.txt','r')
for eachline in matches.readlines():
    dict1 = json.loads(eachline)
    MyFuction.convertToFile('K:/14-15matches', dict1)