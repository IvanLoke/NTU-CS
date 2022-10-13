#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define SIZE 1000 //The limit of expression length

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

void in2Pre(char*, char*);

int main()
{
    char infix[SIZE];
    char prefix[SIZE];

    printf("Enter an infix expression:\n");
    scanf("%[^\n]%*c",infix);

    in2Pre(infix,prefix);
    printf("The prefix expression is \n");
    printf("%s\n",prefix);

    return 0;
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

void in2Pre(char* infix, char* prefix)
{
 //Write your code here
    Stack* stack = malloc(sizeof(Stack));
    stack->head = NULL;
    stack->size = 0;
    infix = strrev(infix);
    while(*infix){
            printf("fck\n");
        if(*infix == '('){
            while(peek(*stack) != ')'){
                char top = peek(*stack);
                pop(stack);
                *prefix = top;
                prefix++;
            }
        }
        else if (*infix == ')'){
            push(stack,*infix);
        }
        if(*infix == '+'||*infix == '-'||infix == '*'|| infix == '/'){
            if((*infix == '+'|| *infix == '-')){
                while(!isEmptyStack(*stack)){
                    char top = peek(*stack);
                    pop(stack);
                    *prefix = top;
                    prefix++;
                }
                push(stack,*infix);
            }
            else if(*infix == '/'||*infix == '*'){
                while(!isEmptyStack(*stack)||peek(*stack)== '/'||peek(*stack)=='*'){
                    char top = peek(*stack);
                    pop(stack);
                    *prefix = top;
                    prefix++;
                }
            }
        }
        infix++;
    }

}
