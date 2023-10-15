#pragma once

/**
 * Defines a 3D Vector
*/
class Vector
{
public:
	/**
	 * Vector x,y,z coordinates
	*/
	double x, y, z;
	/**
	 * Vector constructor
	*/
	Vector(double x, double y, double z);
	/**
	 * Vector addition
	*/
	Vector operator + (const Vector& vector);
	/**
	 * Scalar additon
	*/
	Vector operator + (const double& scalar);
	/**
	 * Vector substraction
	*/
	Vector operator - (const Vector& vector);
	/**
	 * Scalar substraction
	*/
	Vector operator - (const double& scalar);
	/**
	 * Vector cross product
	*/
	Vector operator * (const Vector& vector);
	/**
	 * Scalar product
	*/
	Vector operator * (const double& scalar);
	/**
	 * Vector dot product
	*/
	double operator % (const Vector& vector);
	/**
	 * Vector modulus
	*/
	double operator & ();
};

