#include "pch.h"
#include "../Vectors/Vector.cpp"

TEST(AddsTwoVectors, Addition) {
	Vector v1(3.0, 1.0, 2.0);
	Vector v2(1.0, 4.5, 3.0);
	Vector res = v1 + v2;
	EXPECT_EQ(res.x, 4.0);
	EXPECT_EQ(res.y, 5.5);
	EXPECT_EQ(res.z, 5.0);
}

TEST(AddsScalar, Addition) {
	Vector v1(3.0, 1.0, 2.0);
	Vector res = v1 + 7.0;
	EXPECT_EQ(res.x, 10.0);
	EXPECT_EQ(res.y, 8.0);
	EXPECT_EQ(res.z, 9.0);
}

TEST(SubsTwoVectors, Substraction) {
	Vector v1(3.0, 1.0, 2.0);
	Vector v2(1.0, 4.5, 3.0);
	Vector res = v1 - v2;
	EXPECT_EQ(res.x, 2);
	EXPECT_EQ(res.y, -3.5);
	EXPECT_EQ(res.z, -1);
}

TEST(SubsScalar, Substraction) {
	Vector v1(3.0, 1.0, 2.0);
	Vector res = v1 - 7.0;
	EXPECT_EQ(res.x, -4);
	EXPECT_EQ(res.y, -6);
	EXPECT_EQ(res.z, -5);
}

TEST(VectorialProduct, Products) {
	Vector v1(1, 2, 3);
	Vector v2(1, 5, 7);
	Vector res = v1 * v2;
	EXPECT_EQ(res.x, -1.0);
	EXPECT_EQ(res.y, -4.0);
	EXPECT_EQ(res.z, 3);
}

TEST(ScalarProduct, Products) {
	Vector v1(1, 2, 3);
	Vector res = v1 * -2;
	EXPECT_EQ(res.x, -2);
	EXPECT_EQ(res.y, -4);
	EXPECT_EQ(res.z, -6);
}

TEST(CrossProduct, Products) {
	Vector v1(1, 2, 3);
	Vector v2(1, 5, 7);
	double res = v1 % v2;
	EXPECT_EQ(res, 32);
}

TEST(GetModulus, Modulus) {
	Vector v1(2, 4, -2);
	double res = &v1;
	EXPECT_EQ(res, 2 * sqrt(6));
}