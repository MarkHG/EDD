#include <stdio.h>
#include <stdlib.h>

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
	node* n = createNode(n);

	if (l == NULL || n == NULL)
		return;

	l -> tail -> next = n;
	n -> previous = tail;
	tail = n;
	l -> length++;
}