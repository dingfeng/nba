import numpy as np
import matplotlib.pyplot as plt
 
def getData():
    file_object = open("D:/dataToPC", 'r')
    try:
         playername1 = file_object.readline()
         playername2 = file_object.readline()
         for i in range(10):
             line = file_object.readline()
             parts = line.split(',')
         
         dataSub1 = [float(P) for P in parts]
         line = file_object.readline()
         parts = line.split(',')
         dataSub2 = [float(P) for P in parts]
         line = file_object.readline()
         parts = line.split(',')
         dataSub3 = [float(P) for P in parts]
         line = file_object.readline()
         parts = line.split(',')
         dataSub4 = [float(P) for P in parts]
    finally:
        file_object.close()
    
    return playername1, playername2, dataSub1Done, dataSub2Done, dataSub3Done, dataSub4Done
 
if __name__ == '__main__':
    N = 5
    menMeans = (20, 35, 30, 35, 27)
    menStd = (2, 3, 4, 1, 2)
    #menMeans, menStd, womenMeans, womenStd = getData()
 
    ind = np.arange(N)  # the x locations for the groups
    width = 0.35  # the width of the bars
 
    fig, ax = plt.subplots()
    rects1 = ax.bar(ind, menMeans, width, color='b', yerr=menStd, alpha = 0.4)
 
    womenMeans = (25, 32, 34, 20, 25)
    womenStd = (3, 5, 2, 3, 3)
    rects2 = ax.bar(ind + width, womenMeans, width, color='y', yerr=womenStd, alpha = 0.4)
 
    # add some
    ax.set_ylabel('Scores')
    ax.set_title('Scores by group and gender')
    ax.set_xticks(ind + width)
    ax.set_xticklabels(('G1', 'G2', 'G3', 'G4', 'G5'))
 
    ax.legend((rects1[0], rects2[0]), ('Men', 'Women'))
 
def autolabel(rects):
    # attach some text labels
    for rect in rects:
        height = rect.get_height()
        ax.text(rect.get_x() + rect.get_width() / 2., 1.05 * height, '%d' % int(height),
                ha='center', va='bottom')
 
autolabel(rects1)
autolabel(rects2)
 
plt.show()
