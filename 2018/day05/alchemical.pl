#!/usr/bin/perl
use strict;
use warnings;

use DateTime;
use Data::Dumper;
use List::UtilsBy qw(max_by);

my $file = "test.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my $line = <$input>);
close $input or die "can't close $file: $!";
my @polymer = split(//, $line);

my %charmap;
foreach $_ ("a".."z"){
  $charmap{$_} = ord($_);
}
foreach $_ ("A".."Z"){
  $charmap{$_} = ord($_);
}
# =========== Solve the puzzle ============
# Part 1:
react(\@polymer, \%charmap);
print scalar (@polymer);

# Part 2:

# =========== Subroutine declarations ============

sub react {
  # React a string split into an array.
  my $aref = shift; # Reference to array
  my $href = shift; # Reference to charmap hash
  #my @polymer = @$aref;
  #my %charmap = %$href;
  my $cond = "true";
  OUTER: while ($cond eq "true"){
    for (my $i=0; $i < (scalar(@$aref)-1); $i++) {
      TEST: if (abs($href->{@$aref[$i]} - $href->{@$aref[$i+1]}) == 32) {
        # Remove array elements and go to next while loop if i is high
        splice(@$aref, $i, 2);
        $i--;
        goto TEST;
      }
    }
    $cond = "false";
  }
}
