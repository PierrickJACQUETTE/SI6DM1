echo le fichier traite est $1
head -1 $1

java securiteL3.Chiffre c 15 $1> $1.cesar
java securiteL3.Dechiffre c 15 $1.cesar >$1.cesar.clair
if diff $1 $1.cesar.clair >&2
then echo Cesar réussi
else echo Cesar raté
fi
echo "\n"


java securiteL3.Chiffre p "nbvcxwmlkjhgfdsqpoiuytreza" $1> $1.perm
java securiteL3.Dechiffre p "nbvcxwmlkjhgfdsqpoiuytreza" $1.perm > $1.perm.clair
if diff $1 $1.perm.clair >&2
then echo chiffre  permutation réussi 
else echo chiffre permutation raté
fi
echo "\n"

java securiteL3.Chiffre v "pro" $1> $1.vige
java securiteL3.Dechiffre v "pro" $1.vige > $1.vige.clair
if diff $1 $1.vige.clair  >&2
then echo chiffre Vigenère réussi 
else echo chiffre Vigenère raté
fi
echo "\n"

echo decrypt cesar
java securiteL3.Decrypt c $1.cesar 1 ange >$1.clair1
if diff $1 $1.clair1  >&2
then echo decrypt cesar mode 1 réussi
else echo decrypt cesar mode 1 raté
fi
echo "\n"

java securiteL3.Decrypt c $1.cesar 2 >$1.clair2
if diff $1 $1.clair2 >&2
then echo decrypt cesar mode 2 réussi
else echo decrypt cesar mode 2 raté
fi
echo "\n"

java securiteL3.Decrypt c $1.cesar 3 >$1.clair3
if diff $1 $1.clair3 >&2
then echo decrypt cesar mode 3 réussi
else echo decrypt cesar mode 3 raté
fi
echo "\n"

echo decrypt Vigenère
java securiteL3.Decrypt v $1.vige 1 3 >$1.clair 
if diff $1 $1.clair  >&2
then echo decrypt Vigenère réussi
else echo decrypt Vigenère raté
fi
echo "\n"

echo decrypt Vigenère sans taille de clef
java securiteL3.Decrypt v $1.vige 2 >$1.clair
if diff $1 $1.clair  >&2
then echo decrypt Vigenère réussi
else echo decrypt Vigenère raté
fi
echo "\n"

echo decrypt permutation
java securiteL3.Decrypt p $1.perm >$1.clair
if diff $1 $1.clair  >&2
then echo decrypt Permutation réussi
else echo decrypt Permutation raté
fi
echo "\n"
