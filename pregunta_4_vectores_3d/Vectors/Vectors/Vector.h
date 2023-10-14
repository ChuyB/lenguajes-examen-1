#pragma once
class Vector
{
public:
	double x, y, z;
	Vector(double x, double y, double z);
	Vector operator + (const Vector& vector);
	Vector operator + (const double& scalar);
	Vector operator - (const Vector& vector);
	Vector operator - (const double& scalar);
	Vector operator * (const Vector& vector);
	Vector operator * (const double& scalar);
	double operator % (const Vector& vector);
	double operator & ();
};

