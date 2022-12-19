#include <iostream>
#include <string>
#include <list>
#include "animal.h"
using namespace std;
class Dog : public Mammal
{
private:
    string _owner;

public:
    Dog(string n, COLOR c, string owner) : Mammal(n, c) {}
    ~Dog()
    {
        cout << "Destroying dog" << endl;
    }
    void speak() const
    {
        cout << "Woof" << endl;
    }
};

class Lion : public Mammal
{
public:
    Lion(string n, COLOR c) : Mammal(n, c){};
    ~Lion()
    {
        cout << "Destroying Lion" << endl;
    }
    void move() const
    {
        cout << "Lion moves" << endl;
    }
    void speak() const
    {
        cout << "Roar" << endl;
    }
};

class Cat : public Mammal
{
public:
    Cat(string n, COLOR c) : Mammal(n, c){};
    ~Cat()
    {
        cout << "Destroying cat" << endl;
    }
    void move() const
    {
        cout << "Cat moves" << endl;
    }
    void speak() const
    {
        cout << "Meow" << endl;
    }
};