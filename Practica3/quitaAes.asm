; Programa que convierte a may�sculas el texto que se le ha pasado en min�sculas
; Funcionar� mal si metemos cualquier cosa que no sea una letra
in
dup
push -1
eq
bt 50 ; Hemos llegado al final del fichero
push 32
sub
out
jump 0