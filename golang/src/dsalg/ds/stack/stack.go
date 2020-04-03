// Package stack provides implementation of stacks using linked list.
package stack

import (
	"dsalg/ds/ll"
)

type Stack struct {
	Head *ll.Node
}

// Pop removes the top item (the item added last) from the stack.
func (s *Stack) Pop() {
	if s.Head != nil {
		s.Head = s.Head.Next
	}
}

// Push adds the item to the top of the stack.
func (s *Stack) Push(key string) {
	var newNode = &ll.Node{Key: key}
	if s.Head == nil {
		s.Head = newNode
	} else {
		newNode.Next = s.Head
		s.Head.Prev = newNode
		s.Head = newNode
	}
}

func (s *Stack) String() string {
	var l = ll.LinkedList{Head: s.Head}
	return l.String()
}
