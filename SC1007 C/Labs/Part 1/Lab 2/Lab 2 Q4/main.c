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
    int i = 0;
    Stack* stack = malloc(sizeof(Stack));
    stack->head = NULL;
    stack->size = 0;
    infix = strrev(infix);
    char * rev = infix;
    printf("rev is %s\n", rev);
    while(*infix){
        printf("current infix is %s\n", infix);
        printf("current thing is %c\n",*infix);
        if(*infix == '('){
            while(peek(*stack) != ')'){
                char top = peek(*stack);
                printf("top is %c\n", top);
                pop(stack);
                prefix[i] = top;
                i++;
                prefix[i] = '\0';
            }
            pop(stack);
        }
        else if (*infix == ')'){
            push(stack,*infix);
        }
        else if(*infix == '+'||*infix == '-'||*infix == '*'|| *infix == '/'){
            if((*infix == '+'|| *infix == '-')){
                if(peek(*stack) == '+'||peek(*stack)=='-'){
                    push(stack,*infix);
                }
                else{
                        while(!isEmptyStack(*stack)&&peek(*stack) != ')'){
                                if(peek(*stack) == '+'|| peek(*stack)=='-'){
                                    break;
                                }
                            char top = peek(*stack);
                            pop(stack);
                            prefix[i] = top;
                            i++;
                            prefix[i] = '\0';
                    }
                        push(stack,*infix);
                }
            }
            else if(*infix == '/'||*infix == '*'){
                if(!isEmptyStack(*stack)){
                    while(peek(*stack)== '/'||peek(*stack)=='*'){
                        char top = peek(*stack);
                        pop(stack);
                        prefix[i] = top;
                        i++;
                        prefix[i] = '\0';
                        if(isEmptyStack(*stack)){
                            break;
                        }
                    }
                }
                printf("unstuck\n");
                push(stack,*infix);
            }
        }
        else{
            printf("character at infix is %c\n",*infix);
            prefix[i] = *infix;
            i++;
            prefix[i] = '\0';
        }
        infix++;
        printf("string is %s\n", prefix);
    }
    while(!isEmptyStack(*stack)){
        char top = peek(*stack);
        pop(stack);
        prefix[i] = top;
        i++;
        prefix[i] = '\0';
    }
    prefix[i] == '\0';
    prefix = strrev(prefix);

}
