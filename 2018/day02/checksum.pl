#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util "any";

my $file = "input.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";

my $s1 = "fghij";
my $s2 = "fguij";
my $s3 = "abcde";
my $s4 = "axcye";

#my $mask = $s3 ^ $s4;
#while ($mask =~ /[^\0]/g) {
#  print substr($s3, $-[0], 1), ' ', substr($s4,$-[0],1), ' ', $-[0], "\n";
#}

# =========== Solve the puzzle ============
my $checksum = find_checksum(@lines);
print "Part 1:\n";
print $checksum."\n";
print "Part 2:\n";
compare_strings(@lines);


# =========== Subroutine declarations ============
sub find_checksum {
  my $threes = 0;
  my $twos = 0;
  my @in = @_;

  foreach my $line (@in) {
    my %hash;
    my @characters = split //, $line;
    $hash{$_}++ for @characters;
    if (any {$_ eq 2} values %hash) {
      $twos++;
    }
    if (any {$_ eq 3} values %hash) {
      $threes++;
    }
  }
  my $checksum = $twos*$threes;
  return $checksum
}

sub compare_strings {
  my @in = @_;
  my $count = 0;
  my $len = scalar(@in);
  while ($count < $len){
    for (my $i=$count; $i < $len; $i++) {
      for (my $j=$count+1; $j < $len; $j++) {
        my $mask = $in[$i] ^ $in[$j];
        my $check = () = $mask =~ /[^\0]/g;
        if ($check eq 1) {
          print $in[$i]."\n";
          print $in[$j]."\n";
          exit 0;
        }
      }
    }
    $count += 1;
  }
}
