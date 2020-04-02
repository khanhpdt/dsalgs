// Package ll provides linked list data structures.
package ll

// Node represents a node in a linked list.
type Node struct {
	Content string
	Next    *Node
	Prev    *Node
}

// LinkedList represents a doubly linked list.
type LinkedList struct {
	Head *Node
}

// Insert inserts a node with the given content to the linked list.
func (l *LinkedList) Insert(s string) {
	if l.Head == nil {
		l.Head = &Node{Content: s}
	} else {
		var newNode = Node{Content: s, Next: l.Head}

		newNode.Next.Prev = &newNode

		l.Head = &newNode
	}
}

// Search returns the first node with content equal to s.
func (l *LinkedList) Search(s string) *Node {
	for node := l.Head; node != nil; node = node.Next {
		if node.Content == s {
			return node
		}
	}
	return nil
}
