#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct node
{
    int element;
    struct node* next;
    struct node* previous;
} node;

typedef struct list
{
    node* head;
    node* tail;
    int length;
} list;

node* createNode(int);
list* createList(void);
void add(list*, int);
bool isEmpty(list*);
int removeElement(list*, int);
int search(list*, int);
void printList(list*);

int main() {

    return 0;
}

node* createNode(int element)
{
    //if (element == NULL)
    	//return NULL;

    node* newNode = (node*) calloc(1, sizeof(node));

    if (newNode != NULL)
    {
    	newNode -> element = element;
    	newNode -> next= NULL;
    	newNode -> previous = NULL;
    }
    return newNode;
}

list* createList(void)
{
    list* newList = (list*) calloc(1, sizeof(list));

    if (newList != NULL)
    {
    	newList -> head = NULL;
    	newList -> tail = NULL;
    	newList -> length = 0;
    }
    return newList;
}

void add(list* l, int element)
{
	node* n = createNode(element);

	if (l == NULL || n == NULL)
		return;

    if (isEmpty(l))
    {
        l -> head = n;
        l -> tail = n;
    }
    else
    {
        l -> tail -> next = n;
    	n -> previous = l -> tail;
    	l -> tail = n;
    }
	l -> length++;
}

bool isEmpty(list* l)
{
    if (l == NULL || l -> length == 0)
        return true;
    return false;

}

int removeElement(list* l, int n)
{
    if (isEmpty(l) || n < 0 || l -> length < n)
        return -1;

    node* tmp = l -> head;

    for (int i = 0; i < n-1; i++)
    {
        tmp = tmp -> next;
    }

    return 0;
}


int search(list* l, int n)
{
    if (isEmpty(l) || n < 0 || l -> length < n)
        return -1;

    node* tmp = l -> head;

    for (int i = 0; i < n; i++)
        tmp = tmp -> next;

    return tmp -> element;
}
void printList(list* l)
{
    if (isEmpty(l))
        printf("[ ]\n");

    printf("[ ");


}
