package ll

import "testing"

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

	if node.Content != "a" {
		t.Errorf(`Search("a") returns node with content %q, want: "a"`, node.Content)
	}
}

func TestInsert(t *testing.T) {
	var l = new(LinkedList)
	l.Insert("a")
	l.Insert("b")
	l.Insert("c")

	if l.Head.Content != "c" {
		t.Errorf(`head = %q, want: "c"`, l.Head.Content)
	}
	if l.Head.Next.Content != "b" {
		t.Errorf(`head.next = %q, want: "b"`, l.Head.Next.Content)
	}
	if l.Head.Next.Next.Content != "a" {
		t.Errorf(`head.next.next = %q, want: "a"`, l.Head.Next.Next.Content)
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
	if l.Head.Next.Prev.Content != "c" {
		t.Errorf(`head = %q, want: "c"`, l.Head.Content)
	}
	if l.Head.Next.Next.Prev.Content != "b" {
		t.Errorf(`head.next = %q, want: "b"`, l.Head.Next.Next.Prev.Content)
	}
}
