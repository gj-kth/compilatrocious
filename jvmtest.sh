#!/bin/bash
java -cp mjc.jar mjc.JVMMain TestJVM.minij -S
java -jar lib/jasmin-2.4/jasmin.jar *.j
