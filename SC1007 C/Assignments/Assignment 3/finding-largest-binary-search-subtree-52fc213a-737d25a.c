#include <stdio.h>
#include <stdlib.h>

typedef struct _btnode{
    int item;
    struct _btnode *left;
    struct _btnode *right;
} BTNode;

typedef struct _queueNode{
	BTNode *data;
	struct _queueNode *next;
}QueueNode;

typedef struct _queue{
   int size;
   QueueNode *head;
   QueueNode *tail;
} Queue;

//Prototypes of Interface functions for Queue structure
void enqueue(Queue *qPtr, BTNode * data);
int dequeue(Queue *qPtr);
BTNode* getFront(Queue q);
int isEmptyQueue(Queue q);

BTNode* createNode(int item);
void printBTNode(BTNode *root, int space, int left);
void deleteTree(BTNode **root);

BTNode* findLargestBST (BTNode* root);

int main()
{
    BTNode* root = NULL;
    BTNode* temp = NULL;
    Queue q;
    q.head = NULL;
    q.tail = NULL;
    q.size = 0;

    int item;
    int flag=1;
    printf("Enter a list of numbers for a Binary Tree, terminated by any non-digit character: \n");
    while(scanf("%d",&item))
    {
        if(root==NULL)
        {
            if(item!=-1){
                root =createNode(item);
                enqueue(&q,root);
            }
        }
        else{
            while(!isEmptyQueue(q)){
                temp = getFront(q);
                if(flag){
                    if(item != -1){
                        temp->left = createNode(item);
                        enqueue(&q,temp->left);
                    }
                    flag = 0;
                    break;
                }
                else if(temp->right==NULL){
                    if(item!=-1){
                        temp->right = createNode(item);
                        enqueue(&q,temp->right);
                    }
                    dequeue(&q);
                    flag=1;
                    break;
                }
                else  dequeue(&q);
            }
        }
    }
    scanf("%*s");
    printf("The input binary tree:\n");
    printBTNode(root,0,0);
    printf("\n");
    BTNode* BSTnode = findLargestBST (root);
    printf("The largest binary search subtree:\n");
    if(BSTnode)
        printBTNode(BSTnode,0,0);
    else
        printf("No BST\n");
    deleteTree(&root);

    return 0;
}

BTNode* createNode(int item)
{
    BTNode* node = (BTNode*) malloc(sizeof(BTNode));
	node->item = item;
	node->left = node->right = NULL;
	return node;
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

          printf("%d\n", root->item);

          space++;
          printBTNode(root->left, space,1);
          printBTNode(root->right, space,0);
      }
}

void deleteTree(BTNode **root){
    if (*root != NULL)
	{
		deleteTree(&((*root)->left));
		deleteTree(&((*root)->right));
		free(*root);
		*root = NULL;
	}
}
void enqueue(Queue *qPtr, BTNode *data){
    QueueNode *newNode;
    newNode = malloc(sizeof(QueueNode));
    newNode->data = data;
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
    return q.head->data;
}

int isEmptyQueue(Queue q){
    if(q.size==0) return 1;
    else return 0;
}

int countNumNodes(BTNode*root){
    if(root == NULL){
        return 0;
    }
    int random = (countNumNodes(root->left)+countNumNodes(root->right)+1);
    printf("Root is %i\n and sum is %i\n",root->item,random);
    return(countNumNodes(root->left)+countNumNodes(root->right)+1);
}
int binarytreecheck(BTNode*root, BTNode*lower,BTNode*upper){
    if(root == NULL){
        return 1;
    }
    if (lower!=NULL && root->item <= lower->item){
        return 0;
    }
    if (upper!=NULL && root->item >= upper->item){
        return 0;
    }

    return(binarytreecheck(root->left,lower,root) && binarytreecheck(root->right,root,upper));

}
//    int leftchecker, rightchecker;
//    int rootid = root->item;
//    int leftrootid = 0, rightrootid = 0;
//    int isleftoccupied, isrightoccupied;
//    if(root->left != NULL){
//        leftrootid = root->left->item;
//        isleftoccupied = 1;
//    }
//    else{
//        isleftoccupied = 0;
//    }
//    if (root->right != NULL){
//        rightrootid = root->right->item;
//        isrightoccupied = 1;
//    }
//    else{
//        isrightoccupied = 0;
//    }
//
//    if((isleftoccupied && leftrootid>rootid) || (isrightoccupied && rightrootid<rootid)){
//        return 0;
//    }
//    if (!isrightoccupied && !isleftoccupied){
//        return 1;
//    }
//    if(isleftoccupied && leftrootid < rootid){
//        leftchecker = binarytreecheck(root->left);
//        if(leftchecker == 0){
//            return 0;
//        }
//    }
//    if(isrightoccupied && rightrootid > rootid){
//        if(leftchecker == 0){
//            return 0;
//        }
//        rightchecker = binarytreecheck(root->right);
//        if (rightchecker == 0){
//            return 0;
//        }
//    }
//    return 1;



BTNode* findLargestBST (BTNode* root)
{
    if(root == NULL){
        return 0;
    }
// Write your code here
    int currenthighest = 0, currentnodesize = 0;
    BTNode* largestnode;
    BTNode* lower = NULL;
    BTNode* upper = NULL;
    Queue* qPtr = malloc(sizeof(Queue));
    qPtr->size = 0;
    qPtr->head = NULL;
    qPtr->tail = NULL;
    BTNode* node;
    if(root){
        enqueue(qPtr,root);
        while(!isEmptyQueue(*qPtr)){
            node = getFront(*qPtr);
            dequeue(qPtr);
            if(binarytreecheck(node,lower,upper )){
                currentnodesize = countNumNodes(node);
                if (currentnodesize > currenthighest){
                    currenthighest = currentnodesize;
                    largestnode = node;
                }

            }

            if(node->left){
                enqueue(qPtr,node->left);
            }
            if(node->right){
                enqueue(qPtr,node->right);
            }
        }
    }
    return largestnode;
}
