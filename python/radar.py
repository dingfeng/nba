# -*- coding: cp936 -*-
import numpy as np
import matplotlib.pyplot as plt
from numpy.lib.function_base import average

def getData():
    file_object = open("D:/dataToP", 'r')
    try:
         filename = file_object.readline().strip('\n') 
         playername1 = file_object.readline()
         playername2 = file_object.readline()
         #Calculate average performance
         average = np.zeros(5);
         for i in range(5):
             line = file_object.readline()
             parts = line.split(',')
             dataSub = [float(P) for P in parts]
             dataNo = len(dataSub)
             dataSubArray = np.array(dataSub)
             sum1 = dataSubArray.sum()
             mean = sum1/dataNo
             average[i] = mean
             
         #player1data
         line = file_object.readline()
         parts = line.split(',')
         dataSub1 = [float(P) for P in parts]
         dataSub1Done = [dataSub1[0]/average[0], dataSub1[1]/average[1], dataSub1[2]/average[2], dataSub1[3]/average[3], dataSub1[4]/average[4]]
         max1 = max(dataSub1Done)
         line = file_object.readline()
         if line:
             #player2data
             parts = line.split(',')
             dataSub2 = [float(P) for P in parts]
             dataSub2Done = [dataSub2[0]/average[0], dataSub2[1]/average[1], dataSub2[2]/average[2], dataSub2[3]/average[3], dataSub2[4]/average[4]]
             max2 = max(dataSub2Done)
             maximum = max1 if (max1 > max2) else max2
             data = {
                'column names':
                    ['PTS','REB','AST','STL','BLK'],
                'test':
                    [dataSub1Done, dataSub2Done]
             }
         else:
            maximum = max1
            dataSubAveDone = [1, 1, 1, 1, 1]
            data = {
                'column names':
                    ['PTS', 'REB', 'AST', 'STL', 'BLK'],
                'test':
                    [dataSub1Done, dataSubAveDone]
            }

    finally:
        file_object.close()
    
    return filename, data, maximum, playername1, playername2

if __name__ == '__main__':
    N = 5

    filename, data, maximum, player1, player2 = getData()
    spoke_labels = data.pop('column names')
    colors = [(0.12568, 0.6549, 0.9019, 1), (0.8078, 0.8078, 0.8078, 1)]

    angles = np.linspace(0, 2*np.pi, N, endpoint=False)
    angles = np.concatenate((angles, [angles[0]])) # 闭合

    fig = plt.figure()
    ax = fig.add_subplot(111, polar=True)# polar参数！！
    

    for d, color in zip(data['test'], colors):
    	d = np.concatenate((d, [d[0]])) # 闭合
    	ax.plot(angles, d, color = color)
    	ax.fill(angles, d, facecolor = color, alpha = 0.25)
    ax.set_thetagrids(angles * 180/np.pi, spoke_labels, fontsize = 25)
    ax.set_rlim(0, maximum + 0.2)
    ax.grid(True)
    ax.legend((player1, player2), loc=(0.9, .85),labelspacing=0.005, fontsize = 16)
    
    plt.savefig(filename, dpi=30, facecolor='w', edgecolor='w',
        orientation='portrait', papertype=None, format=None,
        transparent=True, bbox_inches=None, pad_inches=0.1,
        frameon=None)
