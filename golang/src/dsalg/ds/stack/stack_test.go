package stack

import "testing"

func TestPush(t *testing.T) {
	var s = new(Stack)
	s.Push("a")
	s.Push("b")

	var stackAsString = s.String()
	if stackAsString != "b -> a" {
		t.Errorf(`stack = %q, want: "b -> a"`, stackAsString)
	}
}

func TestPop(t *testing.T) {
	var s = new(Stack)
	s.Push("a")
	s.Push("b")
	s.Push("c")

	s.Pop()

	var stackAsString = s.String()
	if stackAsString != "b -> a" {
		t.Errorf(`stack = %q, want: "b -> a"`, stackAsString)
	}

	s.Pop()

	stackAsString = s.String()
	if stackAsString != "a" {
		t.Errorf(`stack = %q, want: "a"`, stackAsString)
	}

	s.Pop()

	stackAsString = s.String()
	if stackAsString != "" {
		t.Errorf(`stack = %q, want: ""`, stackAsString)
	}
}
