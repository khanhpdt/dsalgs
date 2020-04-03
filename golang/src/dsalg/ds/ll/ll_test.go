package ll

import (
	"fmt"
	"testing"
)

func TestInsertThenSearch(t *testing.T) {
	var list = new(LinkedList)

	if list.Search("a") != nil {
		t.Error(`Search("a") found, want: not found`)
	}

	list.Insert("a")

	var node = list.Search("a")
	if node == nil {
		t.Error(`Search("a") not found, want: found`)
	}

	if node.Key != "a" {
		t.Errorf(`Search("a") returns node with content %q, want: "a"`, node.Key)
	}
}

func TestInsert(t *testing.T) {
	var l = new(LinkedList)
	l.Insert("a")
	l.Insert("b")
	l.Insert("c")

	if l.Head.Key != "c" {
		t.Errorf(`head = %q, want: "c"`, l.Head.Key)
	}
	if l.Head.Next.Key != "b" {
		t.Errorf(`head.next = %q, want: "b"`, l.Head.Next.Key)
	}
	if l.Head.Next.Next.Key != "a" {
		t.Errorf(`head.next.next = %q, want: "a"`, l.Head.Next.Next.Key)
	}
	if l.Head.Next.Next.Next != nil {
		t.Error("head.next.next.next != nil, want: nil")
	}
}

func TestSetPreviousLink(t *testing.T) {
	var l = new(LinkedList)
	l.Insert("a")
	l.Insert("b")
	l.Insert("c")

	if l.Head.Prev != nil {
		t.Error("head.prev != nil, want: nil")
	}
	if l.Head.Next.Prev.Key != "c" {
		t.Errorf(`head = %q, want: "c"`, l.Head.Key)
	}
	if l.Head.Next.Next.Prev.Key != "b" {
		t.Errorf(`head.next = %q, want: "b"`, l.Head.Next.Next.Prev.Key)
	}
}

func TestDeleteOnlyNode(t *testing.T) {
	var l = new(LinkedList)
	l.Insert("a")

	var deleted = l.Delete("a")

	if deleted == nil {
		t.Error(`Delete("a") returns nil, want: not nil`)
	}
	if l.Head != nil {
		t.Error("list is still not nil, want: nil")
	}
}

func TestDeleteFirstNode(t *testing.T) {
	var l = new(LinkedList)
	l.Insert("a")
	l.Insert("b")

	l.Delete("b")

	if l.Head.Key != "a" {
		t.Errorf(`head = %q, want: "a"`, l.Head.Key)
	}
}

func TestDeleteLastNode(t *testing.T) {
	var l = new(LinkedList)
	l.Insert("a")
	l.Insert("b")

	l.Delete("a")

	if l.Head.Key != "b" {
		t.Errorf(`head = %q, want: "b"`, l.Head.Key)
	}
	if l.Head.Next != nil {
		t.Error("head.next not nil, want: nil")
	}
}

func TestDeleteInMiddleNode(t *testing.T) {
	var l = new(LinkedList)
	l.Insert("a")
	l.Insert("b")
	l.Insert("c")

	l.Delete("b")

	if l.Head.Key != "c" {
		t.Errorf(`head = %q, want: "c"`, l.Head.Key)
	}
	if l.Head.Next.Key != "a" {
		t.Errorf(`head.next = %q, want: "a"`, l.Head.Next.Key)
	}
	if l.Head.Next.Next != nil {
		t.Error("head.next.next != nil, want: nil")
	}
}

func TestToString(t *testing.T) {
	var l = new(LinkedList)
	l.Insert("a")
	l.Insert("b")
	l.Insert("c")

	var s = fmt.Sprintf("%s", l)
	if s != "c -> b -> a" {
		t.Errorf(`got = %s, want: "c -> b -> a"`, s)
	}
}

func TestToStringEmptyList(t *testing.T) {
	var l = new(LinkedList)
	var s = fmt.Sprintf("%s", l)
	if s != "" {
		t.Errorf(`got = %s, want: ""`, s)
	}
}
