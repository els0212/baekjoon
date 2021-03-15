#include <stdio.h>

#define MAXSTRLEN 9
#define ALPHABETNUMBER 26
#define MAXDICTSIZE 200000

int getDictArgs(char dict[MAXDICTSIZE][MAXSTRLEN]){
	int st = 0;

	while (1){
		scanf("%s", dict[st++]);
		if (dict[st - 1][0] == '-')
			break ;
	}
	return (st);
}

int main(void){
	int		size;
	int		checkAvailable;
	int		checkDuplicate;
	char	dict[MAXDICTSIZE][MAXSTRLEN];
	char	puzzle[MAXSTRLEN];
	int		answer[ALPHABETNUMBER];
	int		puzzleAlphaCnt[ALPHABETNUMBER];
	char	arrAlphaCnt[ALPHABETNUMBER];
	int		tempIndex;
	int		min;
	int		max;
	size = getDictArgs(dict);
	while (1){

        // initialize array and parameters
		for(int i = 0; i < ALPHABETNUMBER; i++){
            arrAlphaCnt[i] = 0;
			puzzleAlphaCnt[i] = 0;
			answer[i] = 0;
			if (i < MAXSTRLEN)
				puzzle[i] = 0;
		}
		min = 200001;
		max = -1;

		scanf("%s", puzzle);
		if (puzzle[0] == '#')
			break ;
		// count puzzle alphabet
		for(int i = 0; i < MAXSTRLEN; i++){
			puzzleAlphaCnt[puzzle[i] - 'A']++;
		}

		for(int i = 0; i < size; i++){
			checkAvailable = 1;
            for(int j = 0; j < ALPHABETNUMBER; j++)
            	arrAlphaCnt[j] = 0;
			// count dictionary entry alphabet
			for(int j = 0; j < MAXSTRLEN; j++){
				if (dict[i][j] == '\0')
					break ;
				arrAlphaCnt[dict[i][j] - 'A']++;
			}
			// compare alphabet count
			for(int j = 0; j < MAXSTRLEN; j++){
				tempIndex = dict[i][j] - 'A';
				if (dict[i][j] == '\0')
                    break;
                else if (arrAlphaCnt[tempIndex] > puzzleAlphaCnt[tempIndex]){
					checkAvailable = 0;
					break ;
				}
			}
			if (checkAvailable == 1){
				for(int j = 0; j < MAXSTRLEN; j++){
					tempIndex = dict[i][j] - 'A';
					if (dict[i][j] == '\0')
						break ;

					// check duplicate
					checkDuplicate = 0;
					for(int k = 0; k < j; k++){
						if (dict[i][k] == dict[i][j]){
							checkDuplicate = 1;
							break ;
						}
					}
					// if there is no duplicate, it's ok.
					if (checkDuplicate == 0)
						answer[tempIndex]++;
				}
			}
		}
		// get min & max count
		for(int i = 0; i < MAXSTRLEN; i++){
			tempIndex = puzzle[i] - 'A';
			if (answer[tempIndex] < min)
				min = answer[tempIndex];
			if (answer[tempIndex] > max)
				max = answer[tempIndex];
		}

		// print min value
		for (int i = 0; i < ALPHABETNUMBER; i++){
			if (answer[i] == min && puzzleAlphaCnt[i] != 0)
				printf("%c", i + 'A');
		}
		printf(" %d ", min);

		// print max value
		for (int i = 0; i < ALPHABETNUMBER; i++){
			if (answer[i] == max && puzzleAlphaCnt[i] != 0)
				printf("%c", i + 'A');
		}
		printf(" %d \n", max);
	}
	return (0);
}
