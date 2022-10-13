#include <stdio.h>
#include <stdlib.h>

#define SIZE 1000 //The size of the array

enum ExpType {OPT,OPERAND};

typedef struct _stackNode{
    char item;
    struct _stackNode *next;
}StackNode;

typedef struct _stack{
   int size;
   StackNode *head;
}Stack;

void push(Stack *sPtr, char item);
int pop(Stack *sPtr);
char peek(Stack s);
int isEmptyStack(Stack s);

typedef struct _listnode
{
    int  item;
    enum ExpType type;
	struct _listnode *next;
} ListNode;

typedef struct _linkedlist{
   int size;
   ListNode *head;
} LinkedList;

void insertNode(LinkedList *llPtr, int item,enum ExpType type);
int deleteNode(LinkedList *llPtr);
void removeAllNodes(LinkedList *llPtr);
int isEmptyLinkedList (LinkedList ll);


void in2PreLL(char* infix, LinkedList *inExpLL);

void printExpLL(LinkedList inExp);

int main()
{
    char infix[SIZE];

    //printf("Enter an infix expression:\n");
    scanf("%[^\n]%*c",infix);

    LinkedList inExpLL;
    inExpLL.head = NULL;
    inExpLL.size = 0;

    in2PreLL(infix, &inExpLL);

    printExpLL(inExpLL);

    removeAllNodes(&inExpLL);
    return 0;
}

void printExpLL(LinkedList inExpLL)
{
    ListNode* temp = NULL;
    temp = inExpLL.head;
    while(temp!= NULL){
        if(temp->type == OPERAND)
            printf(" %d ",temp->item);
        else
            printf(" %c ",(char)(temp->item));
        temp = temp->next;
    }
    printf("\n");
}

void insertNode(LinkedList *LLPtr, int item, enum ExpType type) {
    ListNode *newNode;
    newNode = malloc(sizeof(ListNode));
    if(newNode==NULL) exit(0);

    newNode->item = item;
    newNode->type = type;
    newNode->next = LLPtr->head;
    LLPtr->head=newNode;
    LLPtr->size++;
}

int deleteNode(LinkedList *LLPtr) {
    if(LLPtr==NULL || LLPtr->size==0){
        return 0;
    }
    else{
       ListNode *temp = LLPtr->head;
       LLPtr->head = LLPtr->head->next;

       free(temp);
       LLPtr->size--;
       return 1;
    }
}

int isEmptyLinkedList (LinkedList ll) {
    if(ll.size==0) return 1;
    else return 0;
}

void removeAllNodes(LinkedList *LLPtr)
{
	while(deleteNode(LLPtr));
}

void push(Stack *sPtr, char item){
    StackNode *newNode;
    newNode = malloc(sizeof(StackNode));
    newNode->item = item;
    newNode->next = sPtr->head;
    sPtr->head = newNode;
    sPtr->size++;
}

int pop(Stack *sPtr){
    if(sPtr == NULL || sPtr->head == NULL){
        return 0;
    }
    else{
       StackNode *temp = sPtr->head;
       sPtr->head = sPtr->head->next;
       free(temp);
       sPtr->size--;
       return 1;
    }
}

char peek(Stack s){
    return s.head->item;
}

int isEmptyStack(Stack s){
     if(s.size == 0) return 1;
     else return 0;
}


#include<ctype.h>
#include<string.h>
void in2PreLL(char* infix, LinkedList *inExpLL)
{
    char * reverseinfix;
    reverseinfix=infix;
    char temp;
    Stack stack;
    stack.head = NULL;
    stack.size = 0;
    Stack* stackPtr = &stack;

    char temp1;
    int count =0;
    while(reverseinfix[count]!= NULL){
        ++count;
    }

    printf("count %i\n",count);
    int i,j,n;
    n = count;

    for(i=0,j=n-1;i<j;i++,j--){
        temp1=infix[j];
        infix[j]=infix[i];
        infix[i]=temp1;
    }

    int num =0;
    int reverse=0,remainder;
//    strrev(infix);
    while(*infix){
        if(isdigit(*infix)){
            char nextDigit = *(infix+1);
            if (isdigit(nextDigit)){
                while(isdigit(nextDigit)){
                    num = num*10;
                    num = num + (*infix-48);
                    nextDigit = *(infix+1);
                    infix++;
                }
                while (num!=0){
                    remainder = num%10;
                    reverse =  reverse * 10 + remainder;
                    num/=10;
                }
                insertNode(inExpLL,reverse,OPERAND);
                reverse = 0;
                num = 0;
                infix--;
            }
            else{
                insertNode(inExpLL,*infix-'0',OPERAND);
//                infix++;
            }


        }
        else if(*infix == '('){
            while(peek(stack)!=')'){
                temp = peek(stack);
                pop(stackPtr);
                insertNode(inExpLL,temp,OPT);
            }
            pop(stackPtr);
        }

        else if(*infix ==')'){
            push(stackPtr,*infix);
        }
        else if(*infix=='+'||*infix=='-'||*infix=='/'||*infix=='*'){
            if (isEmptyStack(stack)){
                push(stackPtr,*infix);
                printf("go in\n");
            }
            else{
                if(*infix=='-'||*infix=='+'){
                    while((peek(stack)=='*'||peek(stack)=='/')){
                        temp = peek(stack);
                        pop(stackPtr);
                        insertNode(inExpLL,temp,OPT);
                        printf("no\n");
                        if (isEmptyStack(stack)){
                            break;
                        }
                    }
                    push(stackPtr,*infix);
                    printf("go in2\n");
                }
                else if(*infix=='*'||*infix=='/'){
                    push(stackPtr,*infix);
                    printf("go in3\n");
                }

            }
//            insertNode(inExpLL,*infix,OPT);
        }
        infix++;

    }
    while(!isEmptyStack(stack)){
        temp = peek(stack);
        pop(stackPtr);
        insertNode(inExpLL,temp,OPT);
    }

//    temp = peek(stack);
//    pop(stackPtr);
//    printf("top of stack %c\n",peek(stack));
//    printf("temp is %c\n",temp);


  //Write your code here
}
