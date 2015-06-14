import numpy as np
import matplotlib.pyplot as plt
 
def getData():
    file_object = open("D:/dataToPC", 'r')
    try:
         filename = file_object.readline().strip('\n') 
         playername1 = file_object.readline()
         playername2 = file_object.readline()
         AveStd = np.zeros((10, 2));
         maxi = 0.0;
         for i in range(10):
             line = file_object.readline()
             parts = line.split(',')
             dataSub = [float(P) for P in parts]
             dataNo = len(dataSub)
             dataSubArray = np.array(dataSub)
             sum1 = dataSubArray.sum()
             sum2 = (dataSubArray * dataSubArray).sum()
             mean = sum1 / dataNo
             var = sum2 / dataNo - mean ** 2
             std = np.sqrt(var);
             AveStd[i] = np.array([mean, std])
         for j in range(5):
             dataSub1Done = (AveStd[0][0], AveStd[1][0], AveStd[2][0], AveStd[3][0], AveStd[4][0])
             maxi = max(dataSub1Done) if max(dataSub1Done) > maxi else maxi
             dataSub2Done = (AveStd[0][1], AveStd[1][1], AveStd[2][1], AveStd[3][1], AveStd[4][1])
             maxi = max(dataSub2Done) if max(dataSub2Done) > maxi else maxi
             dataSub3Done = (AveStd[5][0], AveStd[6][0], AveStd[7][0], AveStd[8][0], AveStd[9][0])
             maxi = max(dataSub3Done) if max(dataSub3Done) > maxi else maxi
             dataSub4Done = (AveStd[5][1], AveStd[6][1], AveStd[7][1], AveStd[8][1], AveStd[9][1])
             maxi = max(dataSub4Done) if max(dataSub4Done) > maxi else maxi
    finally:
        file_object.close()
    
    return maxi, filename, playername1, playername2, dataSub1Done, dataSub2Done, dataSub3Done, dataSub4Done
 
if __name__ == '__main__':
    N = 5
    maxi, filename, player1name, player2name, player1Means, player1Std, player2Means, player2Std = getData()
 
    ind = np.arange(N)  # the x locations for the groups
    width = 0.35  # the width of the bars
 
    fig, ax = plt.subplots()
    rects1 = ax.bar(ind, player1Means, width, color=(0.12568, 0.6549, 0.9019, 1), yerr=player1Std, alpha=1)
    rects2 = ax.bar(ind + width, player2Means, width, color=(0.8078, 0.8078, 0.8078, 1), yerr=player2Std, alpha=1)
 
    # add some
    ax.set_ylabel('Scores')
    ax.set_xticks(ind + width)
    ax.set_xticklabels(('PTS', 'REB', 'AST', 'FT%', '3PT%'))
    ax.set_yticks(np.linspace(0, maxi + 2, 10))
    ax.legend((player1name, player2name), fontsize=11)
 
def autolabel(rects):
    # attach some text labels
    for rect in rects:
        height = rect.get_height()
        ax.text(rect.get_x() + rect.get_width() / 2., 1.05 * height, '%d' % int(height),
                ha='center', va='bottom')
 
autolabel(rects1)
autolabel(rects2)

plt.savefig(filename, dpi=70, facecolor='w', edgecolor='w',
        orientation='portrait', papertype=None, format=None,
        transparent=True, bbox_inches=None, pad_inches=0.1,
        frameon=None)
