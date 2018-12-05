#!/usr/bin/perl
use strict;
use warnings;

use DateTime;
use Data::Dumper;
use List::UtilsBy qw(max_by);

my $file = "input.txt";
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
my @reacted = react(\@polymer, \%charmap);
print scalar (@reacted);

# Part 2:

# =========== Subroutine declarations ============

sub react {
  # React a string split into an array.
  my $aref = shift; # Reference to array
  my $href = shift; # Reference to charmap hash
  my @polymer = @$aref;
  my $polymer_length = scalar(@polymer);
  my $i = 0;
  while ($i < $polymer_length-1){
    if (abs($href->{$polymer[$i]} - $href->{$polymer[$i+1]}) == 32) {
      # Remove array elements and go to next while loop if i is high
      splice(@polymer, $i, 2);
      $polymer_length -= 2;
      next if ($i == 0);
      $i--;
      next;
    }
    $i++;
  }
  return @polymer
}
