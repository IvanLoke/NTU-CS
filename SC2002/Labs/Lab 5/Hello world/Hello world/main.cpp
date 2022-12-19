#include <iostream>
#include <string>
#include <list>
using namespace std;
enum COLOR { Green, Blue, White, Black, Brown };
class Animal {
public:
	Animal() : _name("unknown") {
		std::cout << "constructing Animal object " << _name << endl;
	}
	Animal(string n, COLOR c) {
		_name = n;
		_color = c;
		std::cout << "Name of Animal is " << n << " Color of animal is " << c << endl;
	}
	~Animal() {
		std::cout << "destructing Animal object " << _name << endl;
	}
	 virtual void speak() const {
		std::cout << "Animal speaks " << endl;
	}
	virtual void move() const = 0;
private:
	string _name;
	COLOR _color;
};
class Mammal : public Animal {
public:
	Mammal(string n, COLOR c) : Animal(n, c) {}
	~Mammal() {
		cout << "destructing Mammal object" << endl;
	}
	void eat() const {
		cout << "Mammal eat" << endl;
	}
	void move() const {
		cout << "Mammal move" << endl;
	}
};

class Dog : public Mammal {
private:
	string _owner;
public:
	Dog(string n, COLOR c, string owner): Mammal(n,c){}
	~Dog() {
		cout << "Destroying dog" << endl;
	}
	void speak() const {
		cout << "Woof" << endl;
	}
};

class Lion : public Mammal {
public:
	Lion(string n, COLOR c) : Mammal(n, c){};
	~Lion() {
		cout << "Destroying Lion" << endl;
	}
	void move() const {
		cout << "Lion moves" << endl;
	}
	void speak() const {
		cout << "Roar" << endl;
	}
};

class Cat : public Mammal {
public:
	Cat(string n, COLOR c): Mammal(n,c) {};
	~Cat() {
		cout << "Destroying cat" << endl;
	}
	void move() const {
		cout << "Cat moves" << endl;
	}
	void speak() const {
		cout << "Meow" << endl;
	}
};

COLOR getcolour() {
	int i = 0;
	cout << "Enter (1) Green, (2) Blue, (3) White (4) Black (5) Brown" << endl;
	cin >> i;
	switch (i) {
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
int main() {
	// Animal a("lion", Blue);
	//Mammal b("cow", Green);
	//Dog d("lol", Green, "urmom");
	//Animal* animalPtr = new Dog("Lassie", White, "Andy");
	//animalPtr->speak();
	//animalPtr->move();
	//Dog dogi("Lassie", White, "Andy");
	//Mammal* aniPtr = &dogi;
	//Mammal& aniRef = dogi;
	//Mammal aniVal = dogi;
	//aniPtr->speak();
	//aniRef.speak();
	//aniVal.speak();

	//a.speak();
	//b.eat();
	//d.speak();

	int x = 0;
	int i = 0;
	Mammal** zoo = new Mammal*[100];
	cout << "Select the animal to send to Zoo:\n(1) Dog (2) Cat (3) Lion (4) Move all animals (5) Quit" << endl;
	cin >> x;
	while (x < 5) {
		string n = "";
		string o = "";
		COLOR c = Green;
		switch (x) {
		case 1:
		{
			cout << "Please enter the dog name" << endl;
			cin >> n;
			//val = static_cast<COLOR>(c);
			cout << "Please enter owner name" << endl;
			cin >> o;
			c = getcolour();
			cout << "Added a Dog" << endl;
			zoo[i] = new Dog(n,c,o);
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
			for (int j = 0; j < i; j++) {
				Mammal* m = zoo[j];
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
	return 0;
}
