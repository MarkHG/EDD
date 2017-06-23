#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct node
{
	void* element;
	struct node* next;
	struct node* previous;
} node;

typedef struct list
{
	node* head;
	node* tail;
	int length;
} list;

node* createNode(void*);
list* createList(void);
void add(list*, void*);
bool isEmpty(list*);
void* remove(list*, int);
void* search(list*, int);
void printList(list*);

int main() {

    return 0;
}

node* createNode(void* element)
{
	if (element == NULL)
		return NULL;

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

void add(list* l, void* element)
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

void* remove(list* l, int n)
{

}


void* search(list*, int n);
void printList(list*);
