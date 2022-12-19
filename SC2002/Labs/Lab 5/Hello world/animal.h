#include <iostream>
#include <string>
#include <list>
using namespace std;
enum COLOR
{
    Green,
    Blue,
    White,
    Black,
    Brown
};
class Animal
{
public:
    Animal() : _name("unknown")
    {
        std::cout << "constructing Animal object " << _name << endl;
    }
    Animal(string n, COLOR c)
    {
        _name = n;
        _color = c;
        std::cout << "Name of Animal is " << n << " Color of animal is " << c << endl;
    }
    ~Animal()
    {
        std::cout << "destructing Animal object " << _name << endl;
    }
    virtual void speak() const
    {
        std::cout << "Animal speaks " << endl;
    }
    virtual void move() const = 0;

private:
    string _name;
    COLOR _color;
};
class Mammal : public Animal
{
public:
    Mammal(string n, COLOR c) : Animal(n, c) {}
    ~Mammal()
    {
        cout << "destructing Mammal object" << endl;
    }
    void eat() const
    {
        cout << "Mammal eat" << endl;
    }
    void move() const
    {
        cout << "Mammal move" << endl;
    }
};