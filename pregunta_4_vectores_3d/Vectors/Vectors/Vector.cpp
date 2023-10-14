#include "Vector.h"
#include <math.h>

Vector::Vector(double x, double y, double z) {
	this->x = x;
	this->y = y;
	this->z = z;
}

Vector Vector::operator + (const Vector& vector) {
	Vector result(x + vector.x, y + vector.y, z + vector.z);
	return result;
}
Vector Vector::operator + (const double& scalar) {
	Vector result(x + scalar, y + scalar, z + scalar);
	return result;
}

Vector Vector::operator - (const Vector& vector) {
	Vector result(x - vector.x, y - vector.y, z - vector.z);
	return result;
}

Vector Vector::operator - (const double& scalar) {
	Vector result(x - scalar, y - scalar, z - scalar);
	return result;
}

Vector Vector::operator* (const Vector& vector) {
	Vector result(y * vector.z - z * vector.y, -(x * vector.z - z * vector.x), x * vector.y - y * vector.x);
	return result;
}

Vector Vector::operator* (const double& scalar) {
	Vector result(x * scalar, y * scalar, z * scalar);
	return result;
}

double Vector::operator% (const Vector& vector) {
	return x * vector.x + y * vector.y + z * vector.z;
}

double Vector::operator & () {
	return sqrt(x * x + y * y + z * z);
}