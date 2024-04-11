#include <iostream>
#include <cmath>

using namespace std;

class Segment {
    double A, B;

public:
    Segment(double A, double B) : A(A), B(B) {}

    Segment operator*(double d) const {
        return {d * A, d * B};
    }

    friend Segment operator*(double d, const Segment& seg) {
        return seg * d;
    }

    Segment operator/(double d) const {
        return {A / d, B / d};
    }

    Segment operator+(double d) const {
        return {A + d, B + d};
    }

    Segment operator-(double d) const {
        return {A - d, B - d};
    }

    friend Segment operator+(double d, const Segment& seg) {
        return seg + d;
    }

    Segment operator+(const Segment& other) const {
        return {min(A, other.A), max(B, other.B)};
    }

    bool operator()(double d) const {
        return (d >= A && d <= B);
    }

    friend ostream& operator<<(ostream& os, const Segment& seg) {
        return os << "[" << seg.A << "," << seg.B << "]";
    }
};

int main() {
    using std::cout; using std::endl;

    Segment seg{2, 3}, s = 1 + 2 * ((seg - 2) / 2 + seg) / 3;

    cout << s << endl << boolalpha;

    for (double x = 0.5; x < 4; x += 1)
        cout << "x=" << x << ": " << s(x) << endl;

    return 0;
}
