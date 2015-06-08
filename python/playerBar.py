import numpy as np
import matplotlib.pyplot as plt
 
def getData():
    file_object = open("D:/playerBar", 'r')
    try:
         playername = file_object.readline()
         #average performance
         average = np.zeros(5);
         for i in range(5):
             line = file_object.readline()
             parts = line.split(',')
             dataSub = [float(P) for P in parts]
             dataSubArray = np.array(dataSub)
             average[i] = dataSubArray.mean()
         line = file_object.readline()
         parts = line.split(',')
         dataSub1 = [float(P) for P in parts]
    finally:
        file_object.close()
    
    return playername, dataSub1, average
 
if __name__ == '__main__':
    N = 5
    playername, playerMeans, averageD = getData()
 
    ind = np.arange(N)  # the x locations for the groups
    width = 0.35  # the width of the bars
 
    fig, ax = plt.subplots()
    rects1 = ax.bar(ind, playerMeans, width, color=(0.12568, 0.6549, 0.9019, 1))
    rects2 = ax.bar(ind + width, averageD, width, color=(0.8078, 0.8078, 0.8078, 1))
 
    # add some
    ax.set_xticks(ind + width)
    ax.set_xticklabels(('PTS', 'REB', 'AST', 'FT%', '3PT%'))
 
    ax.legend((playername, "AVE PERF"), fontsize = 11)
 
def autolabel(rects):
    # attach some text labels
    for rect in rects:
        height = rect.get_height()
        ax.text(rect.get_x() + rect.get_width() / 2., 1.05 * height, '%d' % int(height),
                ha='center', va='bottom')
 
autolabel(rects1)
autolabel(rects2)

plt.savefig("D:/playerData.png", dpi=70, facecolor='w', edgecolor='w',
        orientation='portrait', papertype=None, format=None,
        transparent=True, bbox_inches=None, pad_inches=0.1,
        frameon=None)
plt.show()