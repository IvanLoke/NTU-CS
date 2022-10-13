#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_N 120

typedef struct _btnode{
    char id;
    struct _btnode *left;
    struct _btnode *right;
} BTNode;

void buildTree(BTNode** node, char* preO, char* postO);
void inOrder(BTNode *cur);
void preOrder(BTNode *cur);
void postOrder(BTNode *cur);

int main()
{
    char preO[MAX_N];
    char postO[MAX_N];
    scanf("%s",preO);
    scanf("%s",postO);

    BTNode* root=NULL;
    buildTree(&root,preO,postO);
    if(root==NULL) printf("error\n");
    preOrder(root);
    printf("\n");
    postOrder(root);
    printf("\n");

    inOrder(root);
    printf("\n");

    return 0;
}
void inOrder(BTNode *cur){
    if (cur == NULL)
        return;

    inOrder(cur->left);
    printf("%c",cur->id);
    inOrder(cur->right);
}
void preOrder(BTNode *cur){
    if (cur == NULL)
        return;

    printf("%c",cur->id);
    preOrder(cur->left);
    preOrder(cur->right);
}
void postOrder(BTNode *cur){
    if (cur == NULL)
        return;

    postOrder(cur->left);
    postOrder(cur->right);
    printf("%c",cur->id);
}

BTNode* newNode(char id){
    BTNode* temp = (BTNode*) malloc(sizeof(BTNode*));
    temp->id = id;
    temp->left = NULL;
    temp->right = NULL;
    return temp;
};

void buildTree(BTNode** node, char* preO, char* postO)
{
    char leftPre[120], rightPre[120],leftPost[120],rightPost[120];
    int length = strlen(preO);
    char* precur, *postcur, *prestart,*poststart,*postend;
    BTNode* temp;
    BTNode *root = newNode(*preO);
    root->id = *preO;
    printf("root is %c\n",root->id);
    *node = root;
    precur = prestart = preO, postcur = postO;
    if (length != 1){
        precur++,prestart++;
        postcur[strlen(postcur)-1]='\0';
        poststart = postcur;
    }
    else if(length == 1){
        return;
    }

    printf("post start is %s\n",poststart);
    printf("pre cur is %c\n",*precur);


    int postindex = 0;
    for (int i = 1; i<length; i++){
        if (*precur == *postcur){
            postcur = poststart;
//            printf("FOUND preindex is %i\n",postindex);
//            printf("postcur is %c\n",*postcur);
//            printf("post start is %c\n",*poststart);
            break;
        }
        postcur++;
        postindex++;
//        printf("i is %i\n",i);
    }
    printf("postindex is %i\n",postindex);
    for (int j = 0; j<postindex+1; j++){
        leftPost[j]= *postcur;
        *postcur++;
    }

    printf("leftpost string is %s\n",leftPost);
//    printf("postcur is %c\n",*postcur);
    int rightpostcounter =0;
    while(*postcur){
        rightPost[rightpostcounter] = *postcur;
        rightpostcounter++;
        postcur++;
    }
    rightPost[rightpostcounter] = '\0';
    printf("rightpost string is %s\n",rightPost);
//    if (rightPost[0] == '\0'){
//        printf("yes it is empty\n");
//    }

     postcur--;
    postend = postcur;
//    printf("postend is %c\n",*postend);
//    printf("2.precur is %c\n",*precur);

    int leftprecounter = 0, rightprecounter = 0;
    while(*precur != *postend){
        printf("postend is %c\n",*postend);
        printf("precur is %c\n",*precur);
        leftPre[leftprecounter] = *precur;
        leftprecounter++;
        precur++;
                printf("precur is %c\n",*precur);
    }
    leftPre[leftprecounter]='\0';
    printf("leftpre is %s\n",leftPre);
//    printf("leftcur is %c\n",*precur);
    printf("precur is %c\n", *precur);
    precur--;
    printf("previous precur is %c\n", *precur);
    precur++;
    printf("mother postcur is %c\n",*postcur);
    precur--;
    if (*precur == root->id){
        precur++;
        printf("special\n");
        while(*precur){
            leftPre[rightprecounter] = *precur;
            rightprecounter++;
            precur++;
        }
        leftPre[rightprecounter]='\0';
        rightprecounter = 0;
        printf("special left pre is %s\n", leftPre);
        rightPre[0] = '\0';
    }
    else{
        precur++;
        while(*precur){
            rightPre[rightprecounter] = *precur;
            rightprecounter++;
            precur++;
            rightPre[rightprecounter] = '\0';
        }
    }

    printf("rightpre is %s\n", rightPre);

    if(leftPre[0] != '\0' && rightPre[0] != '\0'){ //means that have both subtree
        buildTree(&(root->left),leftPre,leftPost);
        buildTree(&(root->right),rightPre,rightPost);
    }

    else if(rightPre[0]=='\0'){ //means only have left subtree
        buildTree(&(root->left),leftPre,leftPost);
    }


//    head = malloc(sizeof(BTNode));
//    head->id = *postO;
//    head->left = NULL;
//    head->right = NULL;
//    *node = head;
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
//while(*postO){
//    char temp = *postO; // looping through a string
//    printf("%c\n",*postO);
//    *postO++;
//}

// Write your code here
}
