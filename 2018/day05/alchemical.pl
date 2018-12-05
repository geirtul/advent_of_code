#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util qw(max min);
use List::UtilsBy qw(max_by);

# =========== File import ============
my $file = "input.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my $line = <$input>);
close $input or die "can't close $file: $!";

# =========== Preparation ============
# Set up a hash that maps characters to their numeric value
# to avoid calculating it every time in every loop later.
my %charmap;
my @lowercase = ("a".."z");
my @uppercase = ("A".."Z");
foreach $_ (@lowercase){
  $charmap{$_} = ord($_);
}
foreach $_ (@uppercase){
  $charmap{$_} = ord($_);
}

# =========== Solve the puzzle ============
# Part 1:
my $reacted = react($line, \%charmap);
print "Part 1: ".length($reacted)."\n";

# Part 2:
my @sizes; # Store sizes of reacted strings
for (my $i=0; $i < scalar(@lowercase); $i++) {
  my $l = $lowercase[$i];
  my $u = $uppercase[$i];
  my $tmp = $reacted =~ s/[$l$u]//gr;
  push(@sizes, length(react($tmp, \%charmap)));
}
print "Part 2: ".min(@sizes)."\n";

# =========== Subroutine declarations ============

sub react {
  # React a string
  my $string = shift;
  my $href = shift; # Reference to charmap hash
  my @polymer = split(//, $string); # Split string to array
  my $polymer_length = scalar(@polymer); # keep track of length
  my $i = 0;
  while ($i < $polymer_length-1){
    if (abs($href->{$polymer[$i]} - $href->{$polymer[$i+1]}) == 32) {
      splice(@polymer, $i, 2); # Remove the two elements
      $polymer_length -= 2; # Update current polymer length
      next if ($i == 0); # If first element was removed, stay on index 0.
      $i--; #Jump back one index and go to 'next' while loop.
      next;
    }
    $i++;
  }
  return join("", @polymer);
}
