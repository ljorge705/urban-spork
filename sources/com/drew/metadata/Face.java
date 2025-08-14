package com.drew.metadata;

/* loaded from: classes5.dex */
public class Face {
    private final Age _age;
    private final int _height;
    private final String _name;
    private final int _width;
    private final int _x;
    private final int _y;

    public Age getAge() {
        return this._age;
    }

    public int getHeight() {
        return this._height;
    }

    public String getName() {
        return this._name;
    }

    public int getWidth() {
        return this._width;
    }

    public int getX() {
        return this._x;
    }

    public int getY() {
        return this._y;
    }

    public Face(int i, int i2, int i3, int i4, String str, Age age) {
        this._x = i;
        this._y = i2;
        this._width = i3;
        this._height = i4;
        this._name = str;
        this._age = age;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Face face = (Face) obj;
        if (this._height != face._height || this._width != face._width || this._x != face._x || this._y != face._y) {
            return false;
        }
        Age age = this._age;
        if (age == null ? face._age != null : !age.equals(face._age)) {
            return false;
        }
        String str = this._name;
        String str2 = face._name;
        return str == null ? str2 == null : str.equals(str2);
    }

    public int hashCode() {
        int i = ((((((this._x * 31) + this._y) * 31) + this._width) * 31) + this._height) * 31;
        String str = this._name;
        int iHashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        Age age = this._age;
        return iHashCode + (age != null ? age.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("x: ");
        sb.append(this._x);
        sb.append(" y: ").append(this._y);
        sb.append(" width: ").append(this._width);
        sb.append(" height: ").append(this._height);
        if (this._name != null) {
            sb.append(" name: ").append(this._name);
        }
        if (this._age != null) {
            sb.append(" age: ").append(this._age.toFriendlyString());
        }
        return sb.toString();
    }
}
