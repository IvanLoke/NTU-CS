#include <stdio.h>
#include <stdlib.h>

typedef struct _btnode{
    int nodeV;
    struct _btnode *left;
    struct _btnode *right;
} BTNode;

typedef struct _queuenode{
    BTNode* node;
    struct _queuenode *next;
}QueueNode;

typedef struct _queue{
   int size;
   QueueNode *head;
   QueueNode *tail;
} Queue;

void printBTNode(BTNode *root, int space,int left);
//Prototypes of Interface functions for Queue structure
void enqueue(Queue *qPtr, BTNode * node);
int dequeue(Queue *qPtr);
BTNode* getFront(Queue q);
int isEmptyQueue(Queue q);

int twoNodesCost(BTNode* node, int nodeV1,int nodeV2);

int main()
{
    BTNode* root = (BTNode*) malloc(sizeof(BTNode));

    Queue q;
    q.head = NULL;
    q.tail = NULL;
    q.size = 0;

    BTNode* node;
    enqueue(&q,root);

    int nodeV;
    char opL, opR;

    while(!isEmptyQueue(q)){
            scanf("%d %c %c",&nodeV,&opL,&opR);
            node = getFront(q);dequeue(&q);
            node->nodeV = nodeV;

            if(opL != '@'){
                node->left = (BTNode*) malloc(sizeof(BTNode));
                enqueue(&q,node->left);
            }
            else node->left =NULL;
            if(opR != '@'){
                node->right = (BTNode*) malloc(sizeof(BTNode));
                enqueue(&q,node->right);
            }
            else
                node->right = NULL;
    }

    int v1,v2;
    scanf("%d %d",&v1,&v2);
    int cost = twoNodesCost(root,v1,v2);

    printBTNode(root,0,0);

    printf("Distance is %d\n",cost);
    return 0;
}

void enqueue(Queue *qPtr, BTNode *node){
    QueueNode *newNode;
    newNode = malloc(sizeof(QueueNode));
    newNode->node = node;
    newNode->next = NULL;

    if(isEmptyQueue(*qPtr))
        qPtr->head=newNode;
    else
        qPtr->tail->next = newNode;

    qPtr->tail = newNode;
    qPtr->size++;
}

int dequeue(Queue *qPtr){
    if(qPtr==NULL || qPtr->head==NULL){ //Queue is empty or NULL pointer
        return 0;
    }
    else{
       QueueNode *temp = qPtr->head;
       qPtr->head = qPtr->head->next;
       if(qPtr->head == NULL) //Queue is emptied
           qPtr->tail = NULL;

       free(temp);
       qPtr->size--;
       return 1;
    }
}

BTNode* getFront(Queue q){
    return q.head->node;
}

int isEmptyQueue(Queue q){
    if(q.size==0) return 1;
    else return 0;
}
void printBTNode(BTNode *root,int space,int left){
      if (root != NULL)
      {

          int i;
          for (i = 0; i < space-1; i++)
                 printf("|\t");


          if(i<space){
            if(left==1)
              printf("|---");
            else
              printf("|___");
          }

          printf("%d\n", root->nodeV);

          space++;
          printBTNode(root->left, space,1);
          printBTNode(root->right, space,0);
      }
}

BTNode* findLCA(BTNode*node,int n1,int n2){
    if (node == NULL){
        return NULL;
    }
    if (node->nodeV == n1){
        return node;
    }
    if (node->nodeV == n2){
        return node;
    }
    BTNode* leftLCA = findLCA(node->left,n1,n2);
    BTNode* rightLCA = findLCA(node->right,n1,n2);

    if(leftLCA != NULL && rightLCA != NULL){
        return node;
    }

    return leftLCA != NULL ? leftLCA : rightLCA;
}

int calculatetoll(BTNode* root, int total, int n1, int n2){

    if (root == NULL){
        return NULL;
    }
    if (root==n1){
        total = total + root->nodeV;
        return root;
    }
    if (root == n2){
        total = total + root->nodeV;
        return root;
    }
    BTNode* leftside =calculatetoll(root->left,total,n1,n2);
    BTNode* rightside = calculatetoll(root->right,total,n1,n2);

    if (leftside != NULL && rightside != NULL){
        total = total + root->nodeV;
        return total;
    }
    return leftside != NULL ? leftside : rightside;
}

int twoNodesCost(BTNode* node, int nodeV1,int nodeV2)
{
    BTNode* LCA = findLCA(node,nodeV1,nodeV2);
    int test = 0, sum = 0;
    sum = calculatetoll(LCA,test,nodeV1,nodeV2);
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
    return 18;

// Write your code here
}
