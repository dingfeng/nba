import numpy as np
import matplotlib.pyplot as plt
 
def getData():
    file_object = open("D:/teamCompare", 'r')
    maxi = 0;
    try:
         teamname1 = file_object.readline()
         teamname2 = file_object.readline()
         #team1 performance
         line = file_object.readline()
         parts = line.split(',')
         dataSub1 = [float(P) for P in parts]
         #team2 performance
         line = file_object.readline()
         parts = line.split(',')
         dataSub2 = [float(P) for P in parts]
         maxi = max(dataSub1) if(max(dataSub1) > max(dataSub2))  else max(dataSub2)
    finally:
        file_object.close()
    
    return teamname1, teamname2, dataSub1, dataSub2, maxi
 
if __name__ == '__main__':
    N = 5
    teamname1, teamname2, team1Means, team2Means, maxi = getData()
 
    ind = np.arange(N)  # the x locations for the groups
    width = 0.4  # the width of the bars
 
    fig, ax = plt.subplots()
    rects1 = ax.bar(ind, team1Means, width, color=(0.12568, 0.6549, 0.9019, 1))
    rects2 = ax.bar(ind + width, team2Means, width, color=(0.8078, 0.8078, 0.8078, 1))
 
    # add some
    ax.set_xticks(ind + width)
    ax.set_xticklabels(('PTS', 'REB', 'AST', 'FT%', '3PT%'))
    ax.set_yticks(np.linspace(0, maxi + 5, 8))
    ax.legend((teamname1, teamname2), fontsize = 11)
 
def autolabel(rects):
    # attach some text labels
    for rect in rects:
        height = rect.get_height()
        ax.text(rect.get_x() + rect.get_width() / 2., 1.05 * height, '%d' % int(height),
                ha='center', va='bottom')
 
autolabel(rects1)
autolabel(rects2)

plt.savefig("D:/teamC.png", dpi=70, facecolor='w', edgecolor='w',
        orientation='portrait', papertype=None, format=None,
        transparent=True, bbox_inches=None, pad_inches=0.1,
        frameon=None)
plt.show()