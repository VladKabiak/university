#include <iostream>
#include <iomanip>

int pack(int fromy, int fromm, int fromd, int toy, int tom, int tod) {
    int packedDate = 0;

    packedDate |= (toy - 2000) << 23;   // 7 bits for year
    packedDate |= tom << 19;            // 4 bits for month
    packedDate |= tod << 14;            // 5 bits for day

    packedDate |= (fromy - 2000) << 7;  // 7 bits for year
    packedDate |= fromm << 3;           // 4 bits for month
    packedDate |= fromd;                 // 5 bits for day

    return packedDate;
}

void showPeriod(int period) {
    int fromd = period & 0b11111;
    int fromm = (period >> 3) & 0b1111;
    int fromy = 2000 + ((period >> 7) & 0b1111111);

    int tod = (period >> 14) & 0b11111;
    int tom = (period >> 19) & 0b1111;
    int toy = 2000 + ((period >> 23) & 0b1111111);

    std::cout << fromy << '/' << std::setw(2) << std::setfill('0') << fromm << '/' << std::setw(2) << fromd
              << '-' << toy << '/' << std::setw(2) << std::setfill('0') << tom << '/' << std::setw(2) << tod << std::endl;
}

int main() {
    int period = pack(2000, 2, 3, 2127, 11, 29);
    showPeriod(period);

    return 0;
}