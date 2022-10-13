#include <stdio.h>
#include <stdlib.h>

typedef struct _dbllistnode
{
    int  item;
	struct _dbllistnode *next;
	struct _dbllistnode *pre;
} CDblListNode;

typedef struct _dbllinkedlist{
   int size;
   CDblListNode *head;
} CDblLinkedList;

void insertNode_AtFront(CDblLinkedList *ptrCDLL, int value);
void deleteList(CDblLinkedList *ptrCDLL);
void printList(CDblLinkedList CDLL);

int numMountainPairs(CDblLinkedList CDLL);

int main()
{
    CDblLinkedList himalayas;
    himalayas.head = NULL;
    himalayas.size = 0;

    int item;
    int i,cases;
    int numP;
    scanf("%d",&cases);
    for(i=0; i<cases; i++)
    {
        while(scanf("%d",&item))
            insertNode_AtFront(&himalayas, item);
        scanf("%*s");

        numP = numMountainPairs(himalayas);
        printList(himalayas);
        printf("%d Pairs.\n",numP);

        deleteList(&himalayas);
    }
    return 0;
}

void insertNode_AtFront(CDblLinkedList *ptrCDLL, int value){
      //create a new node
      CDblListNode* newNode;
      newNode = (CDblListNode *) malloc(sizeof(CDblListNode));
      newNode->item = value;

      if(ptrCDLL->size==0) //first node
      {
          newNode->next = newNode;
          newNode->pre = newNode;
          ptrCDLL->head = newNode;
      }
      else{
        newNode->next = ptrCDLL->head;
        newNode->pre = ptrCDLL->head->pre;

        newNode->pre->next = newNode; //update last node next link;
        ptrCDLL->head->pre = newNode;

        ptrCDLL->head = newNode;
      }
      ptrCDLL->size++;
}

void printList(CDblLinkedList CDLL){

    if(CDLL.head==NULL) return;

    CDblListNode* temp = CDLL.head;
    printf("Current List: ");

    while (temp->next != CDLL.head){
        printf("%d ", temp->item);
        temp = temp->next;

    }
    printf("%d\n",temp->item);
}

void deleteList(CDblLinkedList *ptrCDLL){
    if(ptrCDLL->head==NULL) return;
    CDblListNode *cur = ptrCDLL->head;
    CDblListNode *temp;

    while (cur->next!= ptrCDLL->head) {
        temp=cur->next;
        free(cur);
        cur=temp;
    }

    free(cur);
    ptrCDLL->head =NULL;
    ptrCDLL->size =0;

}



int numMountainPairs(CDblLinkedList CDLL)
{
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
    CDblListNode* startingheader = CDLL.head;
    CDblListNode* temp, *cur,*previous,*after,*jump, *endpoint, *heightmax, *heightmaxtemp, *startingprevious;
    int mountaincount =0, pairs = 0, check = 0, heightmin = 0, looppairs = 0, timesrun =0, reverseheightmax =0;
    temp = startingheader;
    do{
        temp = temp->next;
        mountaincount++;
    }
    while(temp != startingheader);
    switch(mountaincount){
    case 2:
        return 1;
    case 3:
        return 3;
    }
    pairs = pairs + mountaincount;

    if (pairs == 2){
        return 1;
    }

    cur = startingheader;
    previous = startingheader->pre;
    after = startingheader->next;
    printf("current is %i, previous is %i, next is %i\n",cur->item,previous->item,after->item);
    printf("starting header is %i\n",startingheader->item);
    endpoint = startingheader->pre;
    printf("end point is %i\n",endpoint->item);
    heightmax = 0;
    for(int i =0;i< mountaincount-1;i++){
        while(cur != endpoint){
            printf("cur is %i\n",cur->item);
//            printf("i is %i\n",i);
            heightmin = (startingheader->item<= after->item)? startingheader->item : after->item;
            printf("heightmin is %i\n:",heightmin);
            heightmaxtemp =(heightmax >= after->pre->item)?heightmax: after->pre->item;
            printf("heightmaxtemp is %i\n",heightmaxtemp);
            if (cur == startingheader){
                heightmax=after->item;
                looppairs++;
                printf("starting header is %i, after is %i, cur is %i\n", startingheader->item, after->item,cur->item);
                printf("special loop pair i is %i\n",i);
                printf("Total number of pairs is %i\n",looppairs);
                cur = after;
                after = after->next;
                continue;
            }
            if(cur->pre == startingheader){
                heightmax = heightmax;
            }
            else if (heightmaxtemp > heightmax){
                heightmax = heightmaxtemp;
            }
            printf("heightmax is %i\n",heightmax);
            if(heightmax <= heightmin){
                looppairs++;
                printf("starting header is %i, after is %i, cur is %i\n", startingheader->item, after->item,cur->item);
                printf("i is %i\n",i);
                printf("Total number of pairs is %i\n",looppairs);
            }
            else{
                printf("welcome\n");
//                printf("current is %i\n",cur->item);
                reverseheightmax = 0;
                startingprevious = startingheader->pre;
                previous = startingprevious;
                printf("Starting previous is %i\n",startingprevious->item);
                while(previous != after){
                    heightmaxtemp =(reverseheightmax >= previous->item)?reverseheightmax : previous->item;
                    if(previous == startingprevious){
                        reverseheightmax = previous->item;
                        previous = previous->pre;
                        continue;
                    }
                    if(heightmaxtemp > reverseheightmax){
                        reverseheightmax= heightmaxtemp;
                    }
                    previous = previous->pre;
                }
                printf("reverse heightmax is %i\n",reverseheightmax);
                printf("current heightmin is %i\n",heightmin);
                if (reverseheightmax <=heightmin){
                    looppairs++;
                    printf("starting header is %i, after is %i, cur is %i\n", startingheader->item, after->item,cur->item);
                    printf("i is %i\n",i);
                    printf("Total number of pairs is %i\n",looppairs);
                }
                else if(after == startingprevious){
                    looppairs++;
                    printf("starting header is %i, after is %i, cur is %i\n", startingheader->item, after->item,cur->item);
                    printf("i is %i\n",i);
                    printf("Total number of pairs is %i\n",looppairs);
                }

            }
            cur = after;
            after = after->next;
            printf("END ITERATION OF WHILE LOOP\n");
            }
        printf("Reach endpoint\n");
        reverseheightmax = 0;
        heightmax = 0;
//        cur = startingheader;
//        cur = cur->next;
//        after = cur->next->next;
        previous = startingprevious;
        startingprevious = startingheader;
        startingheader = startingheader->next;
        cur = startingheader;
        after = cur->next;

    }
//add heightmax = 0 at the end of the loop
//    if(cur->item >= after->item){
//        heightmin = after->item;
//        previous = cur;
//        cur = after;
//        after = after->next;
//        while(cur!=startingheader){
//            if(after->item <= heightmin){
//                looppairs++;
//            }
//            else{
//                int backcheck =0;
//                while(previous != after){
//                    if(previous->item <= heightmin){
//                        previous = previous->pre;
//                    }
//                    else{
//                        backcheck++;
//                        break;
//                    }
//                }
//                if(backcheck ==0){
//                    looppairs++;
//                }
//
//            }
//            cur=after;
//            after = after->next;
//
//
//        }
//    }
//    printf("%i\n",looppairs);

    return looppairs;
// Write your code here
}
