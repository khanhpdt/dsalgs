// Package ll provides linked list data structures.
package ll

import "strings"

// Node represents a node in a linked list.
type Node struct {
	Key  string
	Next *Node
	Prev *Node
}

// LinkedList represents a doubly linked list.
type LinkedList struct {
	Head *Node
}

// Insert inserts a node with the given key to the linked list.
func (l *LinkedList) Insert(key string) {
	if l.Head == nil {
		l.Head = &Node{Key: key}
	} else {
		var newNode = Node{Key: key, Next: l.Head}

		newNode.Next.Prev = &newNode

		l.Head = &newNode
	}
}

// Search returns the first node with the given key.
func (l *LinkedList) Search(key string) *Node {
	for node := l.Head; node != nil; node = node.Next {
		if node.Key == key {
			return node
		}
	}
	return nil
}

// Delete deletes the node with given key from the list.
// It returns the deleted node or nil if not found.
func (l *LinkedList) Delete(key string) *Node {
	for node := l.Head; node != nil; node = node.Next {
		if node.Key != key {
			continue
		}

		if node.Next != nil && node.Prev != nil { // in-middle node
			node.Prev.Next = node.Next
			node.Next.Prev = node.Prev
		} else if node.Next != nil && node.Prev == nil { // first node
			l.Head = node.Next
			l.Head.Prev = nil
		} else if node.Next == nil && node.Prev != nil { // last node
			node.Prev.Next = nil
		} else { // the only node in the list
			l.Head = nil
		}
		return node
	}
	return nil
}

func (l *LinkedList) String() string {
	var sb strings.Builder
	var isFirst = true
	for node := l.Head; node != nil; node = node.Next {
		if !isFirst {
			sb.WriteString(" -> ")
		}
		sb.WriteString(node.Key)

		isFirst = false
	}
	return sb.String()
}
