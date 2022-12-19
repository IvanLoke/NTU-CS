#include <iostream>
#include <string>
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
        cout << "constructing Animal object " << _name << endl;
    }
    ~Animal()
    {
        cout << "destructing Animal object " << _name << endl;
    }
    void speak() const
    {
        cout << "Animal speaks " << endl;
    }
    void move() const {}

private:
    string _name;
    COLOR _color;
};
int main()
{
    Animal a;
    a.speak();
    cout << "Program exiting …. " << endl;
    return 0;
}
