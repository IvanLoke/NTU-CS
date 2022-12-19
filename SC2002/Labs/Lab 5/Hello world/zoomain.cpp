#include <iostream>
#include <string>
#include <list>
#include "newanimal.h"
using namespace std;
COLOR getcolour()
{
    int i = 0;
    cout << "Enter (1) Green, (2) Blue, (3) White (4) Black (5) Brown" << endl;
    cin >> i;
    switch (i)
    {
    case 1:
        return Green;
        break;
    case 2:
        return Blue;
        break;
    case 3:
        return White;
        break;
    case 4:
        return Black;
        break;
    case 5:
        return Brown;
        break;
    }
}
int main()
{
    // Animal a("lion", Blue);
    // Mammal b("cow", Green);
    // Dog d("lol", Green, "urmom");
    // Animal* animalPtr = new Dog("Lassie", White, "Andy");
    // animalPtr->speak();
    // animalPtr->move();
    // Dog dogi("Lassie", White, "Andy");
    // Mammal* aniPtr = &dogi;
    // Mammal& aniRef = dogi;
    // Mammal aniVal = dogi;
    // aniPtr->speak();
    // aniRef.speak();
    // aniVal.speak();

    // a.speak();
    // b.eat();
    // d.speak();

    int x = 0;
    int i = 0;
    Mammal **zoo = new Mammal *[100];
    cout << "Select the animal to send to Zoo:\n(1) Dog (2) Cat (3) Lion (4) Move all animals (5) Quit" << endl;
    cin >> x;
    while (x < 5)
    {
        string n = "";
        string o = "";
        COLOR c = Green;
        switch (x)
        {
        case 1:
        {
            cout << "Please enter the dog name" << endl;
            cin >> n;
            // val = static_cast<COLOR>(c);
            cout << "Please enter owner name" << endl;
            cin >> o;
            c = getcolour();
            cout << "Added a Dog" << endl;
            zoo[i] = new Dog(n, c, o);
            i++;
            break;
        }

        case 2:
            cout << "Please enter name for Cat" << endl;
            cin >> n;
            c = getcolour();
            zoo[i] = new Cat(n, c);
            i++;
            cout << "Added a Cat" << endl;
            break;
        case 3:
            cout << "Please enter name for Lion" << endl;
            cin >> n;
            c = getcolour();
            zoo[i] = new Lion(n, c);
            i++;
            cout << "Added a Lion" << endl;
            break;
        case 4:
            for (int j = 0; j < i; j++)
            {
                Mammal *m = zoo[j];
                m->move();
                m->speak();
                m->eat();
            }
            cout << "Moved all animals" << endl;
            break;
        }
        cin >> x;
    }

    cout << "Program exiting ..." << endl;

    for (int k = 0; k < i; k++)
    {
        delete zoo[k];
    }
    delete zoo;

    return 0;
}